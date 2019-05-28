package co.maxbi.logic.workflow.fasade;

import co.maxbi.logic.workflow.AccrualsAddExp;
import co.maxbi.rest.SharePointUpdater;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Клас-фасад, агрегує в собі лоігку запуску проведення розрахунків по доданих витратах.
 *
 */
public class AddExpFasade {

    final static Logger logger = Logger.getLogger(AddExpFasade.class);

    private String periodId;
    private String periodName;
    private String periodStartDateStr;
    private String periodEndDateStr;

    private static final String ADD_EXP_STATUS = "OData__x0414__x043e__x043f__x0420__x04";
    private String failStatus = "ОШИБКА";
    private String successStatus = "ДА";

    public AddExpFasade(String periodId, String periodName, String periodStartDateStr, String periodEndDateStr) {
        this.periodId = periodId;
        this.periodName = periodName;
        this.periodStartDateStr = periodStartDateStr;
        this.periodEndDateStr = periodEndDateStr;
    }
    public void run() {
        SharePointUpdater updater = new SharePointUpdater(successStatus, failStatus, periodId);
        AccrualsAddExp accrualsAddExp = new AccrualsAddExp();

        try {
            accrualsAddExp.calculateAddExp(periodId, periodName, periodStartDateStr, periodEndDateStr);
            logger.info("Successful closed Add Expenses!");
           // updater.sendStatusOK(ADD_EXP_STATUS);
        } catch (SQLException e) {
         //   updater.sendStatusFAIL(ADD_EXP_STATUS);
            logger.error("SQL problem", e);
        } catch (NullPointerException e) {
         //   updater.sendStatusFAIL(ADD_EXP_STATUS);
            logger.error("NullPointerException", e);
        } catch (Exception e) {
        //    updater.sendStatusFAIL(ADD_EXP_STATUS);
            logger.error("Exception", e);
        }


    }



}

