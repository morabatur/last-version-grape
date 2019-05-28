package co.maxbi.logic.workflow;


import co.maxbi.autorization.TokenMaster;
import co.maxbi.converter.date.DateOperations;
import co.maxbi.converter.date.TimeZoneConverter;
import co.maxbi.db.dao.DBRequestClosedEntriesExpenses;
import co.maxbi.db.pojo.global.ClosedEntriesExpenses;
import co.maxbi.db.pojo.local.ClosedExpensesPeriods;
import co.maxbi.logic.workflow.calculate.DurationCalculator;
import co.maxbi.rest.Catalog;
import co.maxbi.rest.DataLoader;
import co.maxbi.rest.Parser;
import co.maxbi.rest.entity.addExp.JsonDataAddExp;
import co.maxbi.rest.entity.addExp.Result;
import co.maxbi.rest.entity.dictionary.DictionaryPOJO;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Класть містить в собі логіку для проведення розрахунків доданих витрат
 */
public class AccrualsAddExp {
    private static final Logger logger = Logger.getLogger(AccrualsAddExp.class.getName());

    private Date periodStart;
    private Date periodEnd;


    public void calculateAddExp(String periodID, String periodName, String periodStartStr, String periodEndStr) throws IOException, SQLException {
        String mainUrl = requestLink(periodEndStr, periodStartStr);//Перша ініціалізація, в подальшому буде обнулятись
        logger.info("MY LINK = " + mainUrl);
        //Блок для роботи з завантаженням інформації

        periodStart = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodStartStr));
        periodEnd = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodEndStr));

        logger.info("periodStart = " + periodStart);
        logger.info("periodEnd = " + periodEnd);

        List<ClosedEntriesExpenses> exportList = new ArrayList<>();

        Map<Integer, DictionaryPOJO> mapDictionary = Catalog.getInstance().getMapDictionary();
        while (mainUrl != null) {
            TokenMaster tokenMaster = TokenMaster.create();
            DataLoader jsonDataLoader = new DataLoader(tokenMaster);//Створення загружчика інформації з отриманим до цього токеном
            Parser parserJsonData = new Parser();
//        Передаємос посилання
            JsonReader reader = parserJsonData.dataFromJson(jsonDataLoader.requestJSONData(mainUrl));
            JsonDataAddExp jsonDataExp = parserJsonData.createGson().fromJson(reader, JsonDataAddExp.class);


            logger.info("****************************************");
            logger.info(jsonDataExp.getData().getNextJson());
            logger.info("****************************************");

            mainUrl = jsonDataExp.getData().getNextJson();
            for (int i = 0; i < jsonDataExp.getData().getResults().length; i++) {

                Result result = jsonDataExp.getData().getResults()[i];

                logger.info("Current element = " + i);

                boolean test = result.getIncomeInfo().getEndProject().before(periodStart);
                logger.info("Конец проекта: " + result.getIncomeInfo().getEndProject());
                logger.info("Начало периода: " + periodStart);
                logger.info("Результат: " + test);
                logger.info("----------------------------------------------");
                if (result.getIncomeInfo().getEndProject().before(periodStart)) {
                    logger.info("IF BLOCK!");

                    int idExp = result.getIdExp();
                    logger.info("idExp = " + idExp);

                    double sum = result.getSum();
                    logger.info("sum = " + sum);

                    Date workEnd = result.getWorkEnd();
                    logger.info("workEnd = " + workEnd);

                    int idIncome = result.getIncomeInfo().getId();
                    logger.info("idIncome = " + idIncome);

                    Date begin = result.getIncomeInfo().getBegin();
                    logger.info("begin = " + begin);

                    Date endProject = result.getIncomeInfo().getEndProject();
                    logger.info("endProject = " + endProject);

                    int idCategory = result.getIdCategory();
                    logger.info("idCategory = " + idCategory);

                    Date end = workEnd;

                    String periodStartString = periodStartStr;
                    String periodEndString = periodEndStr;
                    logger.info("periodStartString = " + periodStartString);
                    ClosedExpensesPeriods closedEntriesExpenses = new DBRequestClosedEntriesExpenses().selectClosedProjectData(idExp, periodStartString);

                    double periodExpenseAcc = closedEntriesExpenses.getPeriodExpenseAcc();
                    logger.info("periodExpenseAcc = " + periodExpenseAcc);

                    int durationPeriodAcc = closedEntriesExpenses.getDurationPeriodAcc();
                    logger.info("durationPeriodAcc = " + durationPeriodAcc);

                    Date startMin = closedEntriesExpenses.getStartMin();
                    logger.info("startMin = " + startMin);


                    int duration = DateOperations.diffInDays(begin, end) + 1;
                    logger.info("duration = " + duration);

                    int durationInPeriod = DurationCalculator.calculateDurationVtp(begin, end, periodStart, periodEnd);
                    logger.info("durationInPeriod = " + durationInPeriod);

                    double periodExpenses = (sum - periodExpenseAcc) / duration * durationInPeriod;
                    logger.info("periodExpenses = " + periodExpenses);


                    ClosedEntriesExpenses entriesExpenses = new ClosedEntriesExpenses();


                    entriesExpenses.setExpenseId(idExp);
                    entriesExpenses.setTotalFixSum(sum);
                    entriesExpenses.setFixStart(new Timestamp(begin.getTime()));
                    entriesExpenses.setFixEnd(new Timestamp(end.getTime()));
                    entriesExpenses.setDuration(duration);
                    entriesExpenses.setDurationInPeriod(durationInPeriod);
                    entriesExpenses.setDurationPeriodAcc(durationPeriodAcc);
                    entriesExpenses.setPeriodSum(periodExpenses);
                    entriesExpenses.setFixedExpProjId(idIncome);
                    entriesExpenses.setCorrClosed("false");
                    entriesExpenses.setShifted("false");
                    entriesExpenses.setPeriodId(Double.parseDouble(periodID));
                    entriesExpenses.setPeriodName(periodName);
                    entriesExpenses.setPeriodStart(new Timestamp(periodStart.getTime()));
                    entriesExpenses.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                    entriesExpenses.setPeriodState("Закрыт");

                    entriesExpenses.setPeriodStartStr(periodStartString);
                    entriesExpenses.setPeriodEndStr(periodEndString);
                    entriesExpenses.setFixedExpCategory_GroupId((double)mapDictionary.get(idCategory).getGroupId());
                    entriesExpenses.setFixedExpCategory_GroupName(mapDictionary.get(idCategory).getTitle());

                    exportList.add(entriesExpenses);

                }
            }


        }
        new DBRequestClosedEntriesExpenses().insert(exportList);

    }



    private String requestLink(String periodEnd, String periodStart) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://possiblegroup.sharepoint.com/sites/fintest/_vti_bin/client.svc/web/lists/getbyid(guid'C2DA9BD2-E710-4605-92BE-08621DDE6CC8')/Items?$filter=OData__x041d__x0430__x0447__x0430__x04%20le%20%27");
        stringBuilder.append(periodEnd);//PERIODEND
        stringBuilder.append("%27%20and%20OData__x041e__x043a__x043e__x043d__x04%20ge%20%27");
        stringBuilder.append(periodStart);//PERIODSTART
        stringBuilder.append("%27&$expand=OData__x041f__x0440__x043e__x0435__x04&$select=OData__x041f__x0440__x043e__x0435__x04/ID,OData__x041f__x0440__x043e__x0435__x04/OData__x041d__x0430__x0447__x0430__x04,OData__x041f__x0440__x043e__x0435__x04/OData__x041e__x043a__x043e__x043d__x04,Id,OData__x0421__x0443__x043c__x043c__x04,OData__x041d__x0430__x0447__x0430__x04,OData__x041e__x043a__x043e__x043d__x04,OData__x0422__x0438__x043f__x0020_2_x0Id");

        return stringBuilder.toString();

    }

}
