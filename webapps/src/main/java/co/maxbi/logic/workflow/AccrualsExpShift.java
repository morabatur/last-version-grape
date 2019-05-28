package co.maxbi.logic.workflow;

import co.maxbi.autorization.TokenMaster;
import co.maxbi.converter.date.TimeZoneConverter;
import co.maxbi.db.dao.DBRequestClosedEntriesExpenses;
import co.maxbi.db.pojo.local.ClosedEntriesExpenses;
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
 * Класть містить в собі логіку для проведення розрахунків переведених витрат
 */
public class AccrualsExpShift {
    private static final Logger logger = Logger.getLogger(AccrualsExpShift.class.getName());
    private static final String TOP_5000_EXPENSES = "https://possiblegroup.sharepoint.com/sites/fintest/_vti_bin/client.svc/web/lists/getbyid(guid%27C2DA9BD2-E710-4605-92BE-08621DDE6CC8%27)/Items?%24select=ID,OData__x041f__x0440__x043e__x0435__x04Id,OData__x0421__x0443__x043c__x043c__x04,OData__x0422__x0438__x043f__x0020_2_x0%2FID&%24expand=OData__x0422__x0438__x043f__x0020_2_x0&%24top=5000";

    private Date periodStart;
    private Date periodEnd;


    public void calculateExpShift(String periodID, String periodName, String periodStartStr, String periodEndStr) throws IOException, SQLException {


        TokenMaster tokenMaster = TokenMaster.create();
        DataLoader jsonDataLoader = new DataLoader(tokenMaster);//Створення загружчика інформації з отриманим до цього токеном
        Parser parserJsonData = new Parser();
//      Передаємос посилання
        JsonReader reader = parserJsonData.dataFromJson(jsonDataLoader.requestJSONData(TOP_5000_EXPENSES));
        JsonDataAccrualsExp jsonDataExpShift = parserJsonData.createGson().fromJson(reader, JsonDataAccrualsExp.class);

        List<co.maxbi.db.pojo.global.ClosedEntriesExpenses> closedEntriesExpenses = new ArrayList<>();


        periodStart = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodStartStr));

        periodEnd = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodEndStr));

        Map<Integer, DictionaryPOJO> mapDictionary = Catalog.getInstance().getMapDictionary();

        DBRequestClosedEntriesExpenses entriesExpenses = new DBRequestClosedEntriesExpenses();

        //отримали з БД
        List<ClosedEntriesExpenses> expensesList = entriesExpenses.selectExpensesId(periodStartStr);//"2019-02-01 00:00:00"
        logger.info("list size = " + expensesList.size());
        int i = 0;
        for (ClosedEntriesExpenses element : expensesList) {
            logger.info("Current element: " + i);
            logger.info("Id = " + element.getId() + "; ExpensesId = " + element.getExpenseId());

//fixme якщо елементів не знайдено то просто ставимо нулл


            Result resultSearchByID = searchByIdExp((int) element.getExpenseId(), jsonDataExpShift);

            if (resultSearchByID == null) {
                co.maxbi.db.pojo.global.ClosedEntriesExpenses expenses = new co.maxbi.db.pojo.global.ClosedEntriesExpenses();
                //Пункт 1
                expenses.setExpenseId(element.getExpenseId());
                expenses.setTotalFixSum(element.getTotalFixSum());
                expenses.setFixStart(new Timestamp(element.getFixStart().getTime()));
                expenses.setFixEnd(new Timestamp(element.getFixEnd().getTime()));
                expenses.setDuration(element.getDuration());
                expenses.setDurationInPeriod(element.getDurationInPeriod());
                expenses.setDurationPeriodAcc(element.getDurationPeriodAcc());
                expenses.setPeriodSum(element.getPeriodSum());
                expenses.setFixedExpProjId(element.getFixedExpProjId());
                //змінна верхня має бути локальною, але в ТЗ про неї нічого не кажуть
                expenses.setCorrClosed("true");
                expenses.setShifted("true");

                expenses.setPeriodId(Double.parseDouble(periodID));
                expenses.setPeriodName(periodName);
                expenses.setPeriodStart(new Timestamp(periodStart.getTime()));
                expenses.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                expenses.setPeriodState("Закрыт");
                expenses.setPeriodStartStr(periodStartStr);
                expenses.setPeriodEndStr(periodEndStr);
                expenses.setFixedExpCategory_GroupId(null);
                expenses.setFixedExpCategory_GroupName(null);

                new DBRequestClosedEntriesExpenses().insert(expenses);



                co.maxbi.db.pojo.global.ClosedEntriesExpenses expensesSecond = new co.maxbi.db.pojo.global.ClosedEntriesExpenses();


                expensesSecond.setExpenseId(element.getExpenseId());
                expensesSecond.setTotalFixSum(element.getTotalFixSum());
                expensesSecond.setFixStart(new Timestamp(element.getFixStart().getTime()));
                expensesSecond.setFixEnd(new Timestamp(element.getFixEnd().getTime()));
                expensesSecond.setDuration(element.getDuration());
                expensesSecond.setDurationInPeriod(element.getDurationInPeriod());
                expensesSecond.setDurationPeriodAcc(element.getDurationPeriodAcc());
                expensesSecond.setPeriodSum((0d - element.getPeriodSum()));
                expensesSecond.setFixedExpProjId(element.getFixedExpProjId());
                expensesSecond.setCorrClosed("true");
                expensesSecond.setShifted("true");

                expensesSecond.setPeriodId(Double.parseDouble(periodID));
                expensesSecond.setPeriodName(periodName);
                expensesSecond.setPeriodStart(new Timestamp(periodStart.getTime()));
                expensesSecond.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                expensesSecond.setPeriodState("Закрыт");
                expensesSecond.setPeriodStartStr(periodStartStr);
                expensesSecond.setPeriodEndStr(periodEndStr);
                //Стара версія
//               expensesSecond.setFixedExpCategory_GroupId((double) groupId);
//               expensesSecond.setFixedExpCategory_GroupName(groupName);

                //Нова версія
                expensesSecond.setFixedExpCategory_GroupId((double) element.getFixedExpCategory_GroupId());
                expensesSecond.setFixedExpCategory_GroupName(element.getFixedExpCategory_GroupName());


                DBRequestClosedEntriesExpenses dbExp = new DBRequestClosedEntriesExpenses();
                dbExp.insert(expensesSecond);
                dbExp.updateCorrClosedStatus(element.getId());

                continue;

            }

            int idCategoryJson = resultSearchByID.getIdCategory().getIdCategory();
            logger.info("idCategoryJson = " + idCategoryJson);
            int idProjectJson = resultSearchByID.getIdInc();
            logger.info("idProjectJson = " + idProjectJson);

            String groupName = mapDictionary.get(idCategoryJson).getTitle();
            logger.info("groupName = " + groupName);
            int groupId = mapDictionary.get(idCategoryJson).getGroupId();
            logger.info("groupId = " + groupId);


            if (groupId != (int) element.getFixedExpCategory_GroupId() || idProjectJson != (int) element.getFixedExpProjId()) {
                logger.info("IF BLOCk");
                co.maxbi.db.pojo.global.ClosedEntriesExpenses expenses = new co.maxbi.db.pojo.global.ClosedEntriesExpenses();
                //Пункт 1
                expenses.setExpenseId(element.getExpenseId());
                expenses.setTotalFixSum(element.getTotalFixSum());
                expenses.setFixStart(new Timestamp(element.getFixStart().getTime()));
                expenses.setFixEnd(new Timestamp(element.getFixEnd().getTime()));
                expenses.setDuration(element.getDuration());
                expenses.setDurationInPeriod(element.getDurationInPeriod());
                expenses.setDurationPeriodAcc(element.getDurationPeriodAcc());
                expenses.setPeriodSum(element.getPeriodSum());
                expenses.setFixedExpProjId(element.getFixedExpProjId());
                //змінна верхня має бути локальною, але в ТЗ про неї нічого не кажуть
                expenses.setCorrClosed("true");
                expenses.setShifted("true");

                expenses.setPeriodId(Double.parseDouble(periodID));
                expenses.setPeriodName(periodName);
                expenses.setPeriodStart(new Timestamp(periodStart.getTime()));
                expenses.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                expenses.setPeriodState("Закрыт");
                expenses.setPeriodStartStr(periodStartStr);
                expenses.setPeriodEndStr(periodEndStr);
                //Нова версія
                expenses.setFixedExpCategory_GroupId((double) groupId);
                expenses.setFixedExpCategory_GroupName(groupName);

//                Стара версія
//                expenses.setFixedExpCategory_GroupId((double) element.getFixedExpCategory_GroupId());
//                expenses.setFixedExpCategory_GroupName(element.getFixedExpCategory_GroupName());

                closedEntriesExpenses.add(expenses);

                new DBRequestClosedEntriesExpenses().insert(expenses);


                //Пункт 2

                co.maxbi.db.pojo.global.ClosedEntriesExpenses expensesSecond = new co.maxbi.db.pojo.global.ClosedEntriesExpenses();


                expensesSecond.setExpenseId(element.getExpenseId());
                expensesSecond.setTotalFixSum(element.getTotalFixSum());
                expensesSecond.setFixStart(new Timestamp(element.getFixStart().getTime()));
                expensesSecond.setFixEnd(new Timestamp(element.getFixEnd().getTime()));
                expensesSecond.setDuration(element.getDuration());
                expensesSecond.setDurationInPeriod(element.getDurationInPeriod());
                expensesSecond.setDurationPeriodAcc(element.getDurationPeriodAcc());
                expensesSecond.setPeriodSum((0d - element.getPeriodSum()));
                expensesSecond.setFixedExpProjId(element.getFixedExpProjId());
                expensesSecond.setCorrClosed("true");
                expensesSecond.setShifted("true");

                expensesSecond.setPeriodId(Double.parseDouble(periodID));
                expensesSecond.setPeriodName(periodName);
                expensesSecond.setPeriodStart(new Timestamp(periodStart.getTime()));
                expensesSecond.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                expensesSecond.setPeriodState("Закрыт");
                expensesSecond.setPeriodStartStr(periodStartStr);
                expensesSecond.setPeriodEndStr(periodEndStr);
                //Стара версія
//               expensesSecond.setFixedExpCategory_GroupId((double) groupId);
//               expensesSecond.setFixedExpCategory_GroupName(groupName);

                //Нова версія
                expensesSecond.setFixedExpCategory_GroupId((double) element.getFixedExpCategory_GroupId());
                expensesSecond.setFixedExpCategory_GroupName(element.getFixedExpCategory_GroupName());


                DBRequestClosedEntriesExpenses dbExp = new DBRequestClosedEntriesExpenses();
                dbExp.insert(expensesSecond);
                dbExp.updateCorrClosedStatus(element.getId());


            }
            i++;


        }

        logger.info(closedEntriesExpenses.toString());
    }


    public Result searchByIdExp(int idExp, JsonDataAccrualsExp jsonData) {
        Result result = null;

        for (Result currentResult : jsonData.getD().getResults()) {
            if (currentResult.getIdExp() == idExp) {
                logger.info(currentResult);
                result = currentResult;

            }
        }

        return result;
    }


}
