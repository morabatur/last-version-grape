package co.maxbi.logic.workflow;

import co.maxbi.autorization.TokenMaster;
import co.maxbi.converter.date.DateOperations;
import co.maxbi.converter.date.TimeZoneConverter;
import co.maxbi.db.dao.DBRequestClosedEntriesIncome;
import co.maxbi.db.pojo.global.ClosedEntriesIncome;
import co.maxbi.db.pojo.local.ClosedIncomeSums;
import co.maxbi.logic.workflow.calculate.DurationCalculator;
import co.maxbi.rest.DataLoader;
import co.maxbi.rest.Parser;
import co.maxbi.rest.entity.inc.JsonDataAccrualsIncome;
import co.maxbi.rest.entity.inc.Result;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класть містить в собі логіку для проведення розрахунків доходів періоду

 */

public class Accrualslnc {
    private static final Logger logger = Logger.getLogger(Accrualslnc.class.getName());


    private Date periodStart;
    private Date periodEnd;


    public int calcAccrualslnc(String periodID, String periodName, String periodStatrStr, String periodEndStr) throws IOException, SQLException {
        ArrayList<ClosedEntriesIncome> resultList = new ArrayList<>();//Лист для вставка доходів в БД
        AccrualsExp accrualsExp = new AccrualsExp();//Об'єкт для розрахунку витрат (аналогічно 2 скрипту воркфлов)

        String mainUrl = buildLink(periodStatrStr, periodEndStr);//Побудова посилання за яким треба отримати перший набір даних

        TokenMaster tokenMaster = TokenMaster.create();//Отримання токена
        DataLoader jsonDataLoader = new DataLoader(tokenMaster);//Створення загружчика інформації з отриманим до цього токеном
        Parser parserJsonData = new Parser();//Створення парсера

        while (mainUrl != null) {
            JsonReader reader = parserJsonData.dataFromJson(jsonDataLoader.requestJSONData(mainUrl)); //Отримати перший набір інфо за УРЛом
            JsonDataAccrualsIncome jsonData = parserJsonData.createGson().fromJson(reader, JsonDataAccrualsIncome.class);//Парсимо JSON

            periodStart = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodStatrStr));//конвертувати початок періоду в об'єкт
            periodEnd = Timestamp.valueOf(TimeZoneConverter.convertToLocalDataTime(periodEndStr));

            mainUrl = jsonData.getD().getNextJson();// задаємо наступну сторінку з інформацією. в разі якщо її не буде цикл буде зупинений

            int countOfElements = jsonData.getD().getResults().length;//Записуємо кількість отриманих елементів в окрему змінну
            logger.info("Elemtnts in JSON = " + countOfElements);
            for (int i = 0; i < countOfElements; i++) {
                Result currentItem = jsonData.getD().getResults()[i];//Отримуємо інформацію про поточний елемент
                logger.info("current intem = " + currentItem.toString());

                int id = currentItem.getID();//записуємо id в окрему змінну
                logger.info("id = " + id);
                String title = currentItem.getTitle();
                logger.info("title = " + title);
                double sum = currentItem.getSum();
                logger.info("sum = " + sum);
                double agAg = currentItem.getAgAg();
                logger.info("agAg = " + agAg);
                double dc = currentItem.getDc();
                logger.info("dc = " + dc);
                double cons = currentItem.getCons();
                logger.info("cons = " + cons);
                Date startProject = currentItem.getDateOfStartProject();
                logger.info("startProject = " + startProject);
                Date endProject = currentItem.getDateOfEndProject();
                logger.info("endProject = " + endProject);
                double consActual = currentItem.getConsActual();
                logger.info("consActual = " + consActual);
                double dcActual = currentItem.getDcActual();
                logger.info("dcActual = " + dcActual);
                double agAgActual = currentItem.getAgAgActual();
                logger.info("agAgActual = " + agAgActual);


                Date startMin = startProject;//7. створюємо локальну змінну НачалоМин та присвоюємо їй значення НачалоПроекта
                logger.info("startMin = " + startMin);


                logger.info("Parameters for request in DB: id = " + id + "periodStartString = " + periodStatrStr);
                ClosedIncomeSums closedEntriesIncome = getClosedEntriesIncomeItems(id, periodEndStr);

                logger.info("closedEntriesIncome from DB = " + closedEntriesIncome);

                //Запис отриманих даних в локальні змінні
                double durationPeriodAcc;
                double periodExpensesAcc;
                double periodAgAgAcc;
                double periodDcAcc;
                double periodConsAcc;
                double periodDncAgAgAcc;
                double periodDncConsAcc;
                double periodDncDcAcc;
                Date minPeriodStart = null;//дата старту проекту з бази

                if (closedEntriesIncome != null) {
                    //Запис отриманих даних в локальні змінні
                    durationPeriodAcc = closedEntriesIncome.getSumDurationInPeriod();
                    periodExpensesAcc = closedEntriesIncome.getSumPeriodSum();
                    periodAgAgAcc = closedEntriesIncome.getSumPeriodAgAg();
                    periodDcAcc = closedEntriesIncome.getSumPeriodDc();
                    periodConsAcc = closedEntriesIncome.getSumPeriodCons();
                    periodDncAgAgAcc = closedEntriesIncome.getSumDncAgAgFix();
                    periodDncConsAcc = closedEntriesIncome.getSumDncConsFix();
                    periodDncDcAcc = closedEntriesIncome.getSumDncDcFix();
                    minPeriodStart = closedEntriesIncome.getMinPeriodStart();
                } else {
                    //Запис отриманих даних в локальні змінні
                    durationPeriodAcc = 0;
                    periodExpensesAcc = 0;
                    periodAgAgAcc = 0;
                    periodDcAcc = 0;
                    periodConsAcc = 0;
                    periodDncAgAgAcc = 0;
                    periodDncConsAcc = 0;
                    periodDncDcAcc = 0;
                    minPeriodStart = null;//дата старту проекту з бази

                }

                logger.info("closedEntriesIncome from BD = " + closedEntriesIncome);


                if (minPeriodStart != null && minPeriodStart.before(startMin)) {
                    startMin = minPeriodStart;
                }

                logger.info("startMin (після обрахуваннь) = " + startMin);

                Date begin = calculateBeginDate(startMin, startProject, periodStart);
                Date end = endProject;


                logger.info("DURATION CALCULATE: \n \t begin " + begin + "\n \t end " + end);
                int duration = DateOperations.diffInDays(begin, end) + 1;
                int durationVTP = DurationCalculator.calculateDurationVtp(begin, end, periodStart, periodEnd);
                logger.info("duration " + duration + "\n \t duretionVTP " + durationVTP);

                double restExpenses = sum - periodExpensesAcc;
                logger.info("restExpenses = " + restExpenses);
                double restDuration = duration - durationPeriodAcc;
                logger.info("restDuration = " + restDuration);
                double dayExpenses = restExpenses / (double) duration;
                logger.info("dayExpenses = " + dayExpenses);
                double periodExpenses = dayExpenses * (double) durationVTP;
                logger.info("periodExpenses = " + periodExpenses);
                double restAgAg = agAg - periodAgAgAcc;
                logger.info("restAgAg = " + restAgAg);
                double dayAgAg = restAgAg / (double) duration;
                logger.info("dayAgAg = " + dayAgAg);
                double periodAgAg = dayAgAg * (double) durationVTP;
                logger.info("periodAgAg = " + periodAgAg);
                double restDC = dc - periodDcAcc;
                logger.info("restDC = " + restDC);
                double dayDc = restDC / (double) duration;
                logger.info("dayDc = " + dayDc);
                double periodDc = dayDc * (double) durationVTP;
                logger.info("periodDc = " + periodDc);
                double restCons = cons - periodConsAcc;
                logger.info("restCons = " + restCons);
                double dayCons = restCons / (double) duration;
                logger.info("dayCons = " + dayCons);
                double periodCons = dayCons * (double) durationVTP;
                logger.info("periodCons = " + periodCons);

                double periodDncAgAg = calculatePeriodDncAgAg(agAg, agAgActual, durationVTP, periodDncAgAgAcc, duration);
                logger.info("periodDncAgAg = " + periodDncAgAg);
                double periodDncCons = calculatePeriodDncCons(cons, consActual, periodDncConsAcc, duration, durationVTP);
                logger.info("periodDncCons = " + periodDncCons);
                double periodDncDc = calculatePeriodDncDc(dc, dcActual, periodDncDcAcc, duration, durationVTP);
                logger.info("periodDncDc = " + periodDncDc);


                ClosedEntriesIncome entriesIncome = new ClosedEntriesIncome();//об'єкт для вставки в БД

                entriesIncome.setTotalFixSum(sum);
                entriesIncome.setFixStart(new Timestamp(begin.getTime()));
                entriesIncome.setFixEnd(new Timestamp(end.getTime()));
                entriesIncome.setDuration(duration);
                entriesIncome.setDurationInPeriod(durationVTP);
                entriesIncome.setDurationPeriodAcc(durationPeriodAcc);
                entriesIncome.setPeriodSum(periodExpenses);
                entriesIncome.setProjectId(currentItem.getID());
                entriesIncome.setAgAgFix(currentItem.getAgAg());
                entriesIncome.setDcFix(currentItem.getDc());
                entriesIncome.setConsFix(currentItem.getCons());
                entriesIncome.setPeriodAgAg(periodAgAg);
                entriesIncome.setPeriodDc(periodDc);
                entriesIncome.setPeriodCons(periodCons);
                entriesIncome.setDncAgAgFix(periodDncAgAg);
                entriesIncome.setDncConsFix(periodDncCons);
                entriesIncome.setDncDcFix(periodDncDc);
                entriesIncome.setPeriodId(Double.parseDouble(periodID));
                entriesIncome.setPeriodName(periodName);
                entriesIncome.setPeriodStart(new Timestamp(periodStart.getTime()));
                entriesIncome.setPeriodEnd(new Timestamp(periodEnd.getTime()));
                entriesIncome.setPeriodState("Закрыт");
                entriesIncome.setPeriodStartStr(periodStatrStr);
                entriesIncome.setPeriodEndStr(periodEndStr);

                resultList.add(entriesIncome);



                /*
                 *  Start AccrualsExp
                 */

                accrualsExp.calculate(id, periodID, periodName, periodStart, periodEnd, duration, durationVTP, durationPeriodAcc,
                        startMin, begin, end, periodStatrStr, periodEndStr);


            }


        }
        logger.info("SIZE resultList " + resultList.size());
        int result = new DBRequestClosedEntriesIncome().insert(resultList);//вставити опрацьований елемент доходів в БД
        logger.info("FINAL INSERT COLUMN " + result);

        return result;

    }

    private ClosedIncomeSums getClosedEntriesIncomeItems(int id, String periodStartString) {

        ClosedIncomeSums closedEntriesIncome = new DBRequestClosedEntriesIncome().selectClosedEntriesIncomeItems(Integer.toString(id), periodStartString);//робимо запит до БД та отримуємо інфу по закритих періодах
        return closedEntriesIncome;
    }

    private Date calculateBeginDate(Date startMin, Date startProject, Date periodStart) {
        Date begin;

        logger.info("Method calculateBeginDate:");
        logger.info("\tstartMin = " + startMin);
        logger.info("\tstartProject = " + startProject);
        logger.info("\tperiodStart = " + periodStart);


        if (startMin.after(startProject)) {
            begin = startMin;
        } else {
            begin = startProject;
        }
        if (begin.before(periodStart)) {
            begin = periodStart;
        }
        return begin;
    }

    private double calculatePeriodDncDc(double dc, double dcActual, double periodDncDcAcc, int duration, long durationVTP) {
        double restDcDnc = 0;
        double dayDcDnc = 0;
        double periodDncDc = 0;
        if (dc > dcActual) {
            restDcDnc = dc - dcActual;
            restDcDnc = restDcDnc - periodDncDcAcc;
            dayDcDnc = restDcDnc / (double) duration;
            periodDncDc = dayDcDnc * (double) durationVTP;
        } else {
            periodDncDc = 0 - periodDncDcAcc;
        }
        return periodDncDc;
    }

    private double calculatePeriodDncCons(double cons, double consActual, double periodDncConsAcc, int duration, long durationVTP) {
        double restConsDnc = 0;
        double dayConsDnc = 0;
        double periodDncCons = -1;
        if (cons > consActual) {
            restConsDnc = cons - consActual;
            restConsDnc -= periodDncConsAcc;
            dayConsDnc = restConsDnc / (double) duration;
            periodDncCons = dayConsDnc * (double) durationVTP;
        } else {
            periodDncCons = 0 - periodDncConsAcc;
        }
        return periodDncCons;
    }

    public double calculatePeriodDncAgAg(double agAg, double agAgActual, int durationVTP, double periodDncAgAgAcc, int duration) {
        double restAgAgDnc = 0;
        double dayAgAgDnc = 0;
        double periodDncAgAg = -1;

        logger.info("agAg > agAgActual = " + (agAg > agAgActual));
        if (agAg > agAgActual) {
            restAgAgDnc = agAg - agAgActual;
            restAgAgDnc = restAgAgDnc - periodDncAgAgAcc;
            dayAgAgDnc = restAgAgDnc / (double) duration;
            periodDncAgAg = dayAgAgDnc * (double) durationVTP;

            logger.info("if block");
        } else {
            periodDncAgAg = 0 - periodDncAgAgAcc;
            logger.info("else block");

        }
        return periodDncAgAg;
    }


    public String buildLink(String periodStart, String periodEnd) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://possiblegroup.sharepoint.com/sites/fintest/_vti_bin/client.svc/web/lists/getbyid(guid'E54CA983-C96F-489E-A480-85A85ED33A9F')/Items?$filter=OData__x041d__x0430__x0447__x0430__x04%20le%20%27");
        stringBuilder.append(periodEnd);
        stringBuilder.append("%27%20and%20OData__x041e__x043a__x043e__x043d__x04%20ge%20%27");
        stringBuilder.append(periodStart);
        stringBuilder.append("%27");

        return stringBuilder.toString();
    }


}
