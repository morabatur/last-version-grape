package co.maxbi.logic.workflow.fasade;

import co.maxbi.logic.workflow.AccrualsExpShift;
import co.maxbi.rest.SharePointUpdater;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Клас-фасад, агрегує в собі лоігку запуску проведення розрахунків по переведених витратах.
 *
 */
public class ExpShiftFasade {

    final static Logger logger = Logger.getLogger(ExpShiftFasade.class);

    private String periodId;
    private String periodName;
    private String periodStartDateStr;
    private String periodEndDateStr;

    private static final String EXP_SHIFT_STATUS = "OData__x0418__x0437__x043c__x002e__x00";

    private String failStatus = "ОШИБКА";
    private String successStatus = "ДА";

    public ExpShiftFasade(String periodId, String periodName, String periodStartDateStr, String periodEndDateStr) {
        this.periodId = periodId;
        this.periodName = periodName;
        this.periodStartDateStr = periodStartDateStr;
        this.periodEndDateStr = periodEndDateStr;
    }

    public void run() {
        SharePointUpdater updater = new SharePointUpdater(successStatus, failStatus, periodId);
        AccrualsExpShift accrualsExpShift = new AccrualsExpShift();

        try {
            accrualsExpShift.calculateExpShift(periodId, periodName, periodStartDateStr, periodEndDateStr);
            logger.info("Successful closed Expenses Shift!");
         //   updater.sendStatusOK(EXP_SHIFT_STATUS);
        } catch (SQLException e) {
         //   updater.sendStatusFAIL(EXP_SHIFT_STATUS);
            logger.error("SQL problem", e);
        } catch (NullPointerException e) {
          //  updater.sendStatusFAIL(EXP_SHIFT_STATUS);
            logger.error("NullPointerException", e);
        } catch (Exception e) {
          //  updater.sendStatusFAIL(EXP_SHIFT_STATUS);
            logger.error("Exception", e);
        }

    }


}


