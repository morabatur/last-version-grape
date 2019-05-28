package co.maxbi.logic.workflow;

import co.maxbi.autorization.TokenMaster;
import co.maxbi.db.dao.DBRequestClosedEntriesExpenses;
import co.maxbi.db.pojo.global.ClosedEntriesExpenses;
import co.maxbi.rest.Catalog;
import co.maxbi.rest.DataLoader;
import co.maxbi.rest.Parser;
import co.maxbi.rest.entity.dictionary.DictionaryPOJO;
import co.maxbi.rest.entity.exp.JsonDataAccrualsExp;
import co.maxbi.rest.entity.exp.Result;
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
 * Класть містить в собі логіку для проведення розрахунків витрат періоду
 */
public class AccrualsExp {
    private static final Logger logger = Logger.getLogger(AccrualsExp.class.getName());

    public List<ClosedEntriesExpenses> calculate(int idIncome, String periodID, String periodName, Date periodStart, Date periodEnd,
                                                 int duration, int durationVTPInPeriod, double durationPeriodAcc, Date startMin, Date begin, Date end, String periodStartStr, String periodEndStr) throws IOException, SQLException {
        TokenMaster tokenMaster = TokenMaster.create();
        DataLoader jsonDataLoader = new DataLoader(tokenMaster);//Створення загружчика інформації з отриманим до цього токеном
        Parser parser = new Parser();
        JsonReader reader = parser.dataFromJson(jsonDataLoader.requestJSONData(buildLink(idIncome)));
        logger.info("idIncome = " + idIncome);
        logger.info("SP LINK: " + buildLink(idIncome));
        JsonDataAccrualsExp jsonDataExp = parser.createGson().fromJson(reader, JsonDataAccrualsExp.class);
        logger.info("DATA FROM SP LINK " + idIncome);
        logger.info(jsonDataExp.toString());

        List<ClosedEntriesExpenses> exportCsvList = new ArrayList<>();


        Map<Integer, DictionaryPOJO> mapDictionary = Catalog.getInstance().getMapDictionary();

        for (Result result : jsonDataExp.getD().getResults()) {
            //Парсимо поля JSON та записуємо в локальні змінні
            int idExp = result.getIdExp();
            logger.info("idExp = " + idExp);
            double sum = result.getSumExp();
            logger.info("sum = " + sum);
            int idCategory = result.getIdCategory().getIdCategory();
            logger.info("idCategory = " + idCategory);
            //Отримуємо інформацію про актуальну суму витрат за цей період
            double periodExpenseAcc = new DBRequestClosedEntriesExpenses().selectSumPeriodExpenseAcc(idExp, periodStart.toString());
            logger.info(" periodExpenseAcc = " + periodExpenseAcc);

            double periodExpenses = (sum - periodExpenseAcc) / duration * durationVTPInPeriod;


            ClosedEntriesExpenses closedEntries = new ClosedEntriesExpenses();


            closedEntries.setExpenseId(idExp);
            closedEntries.setTotalFixSum(sum);
            closedEntries.setFixStart(new Timestamp(begin.getTime()));
            closedEntries.setFixEnd(new Timestamp(end.getTime()));
            closedEntries.setDuration(duration);
            closedEntries.setDurationInPeriod(durationVTPInPeriod);
            closedEntries.setDurationPeriodAcc(durationPeriodAcc);
            closedEntries.setPeriodSum(periodExpenses);
            closedEntries.setFixedExpProjId(idIncome);
            closedEntries.setCorrClosed("false");
            closedEntries.setShifted("false");
            closedEntries.setPeriodId(Double.parseDouble(periodID));
            closedEntries.setPeriodName(periodName);
            closedEntries.setPeriodStart(new Timestamp(periodStart.getTime()));
            closedEntries.setPeriodEnd(new Timestamp((periodEnd.getTime())));
            closedEntries.setPeriodState("Закрыт");
            closedEntries.setPeriodStartStr(periodStartStr);
            closedEntries.setPeriodEndStr(periodEndStr);
            closedEntries.setFixedExpCategory_GroupId((double)mapDictionary.get(idCategory).getGroupId());
            closedEntries.setFixedExpCategory_GroupName(mapDictionary.get(idCategory).getTitle());


            exportCsvList.add(closedEntries);
            logger.info("TEMP SIZE LIST: " + exportCsvList.size());


            logger.info("ClosedEntriesExpenses INSERT: ");
            logger.info(closedEntries);


           new DBRequestClosedEntriesExpenses().insert(closedEntries);
        }

        return exportCsvList;
    }




    private String buildLink(int idIncome) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://possiblegroup.sharepoint.com/sites/fintest/_vti_bin/client.svc/web/lists/getbyid(guid'C2DA9BD2-E710-4605-92BE-08621DDE6CC8')/Items?$filter=OData__x041f__x0440__x043e__x0435__x04%20eq%20");
        stringBuilder.append(idIncome);
        stringBuilder.append("&$select=ID,OData__x0421__x0443__x043c__x043c__x04,OData__x0422__x0438__x043f__x0020_2_x0/ID&$expand=OData__x0422__x0438__x043f__x0020_2_x0");

        return stringBuilder.toString();
    }
}
