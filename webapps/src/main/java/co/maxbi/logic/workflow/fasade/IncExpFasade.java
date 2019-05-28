package co.maxbi.logic.workflow.fasade;

import co.maxbi.rest.SharePointUpdater;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Клас-фасад, агрегує в собі лоігку запуску проведення розрахунків надходжень та витрат закритих по періоду
 *
 */
public class IncExpFasade {

    final static Logger logger = Logger.getLogger(IncExpFasade.class);

    private String periodId;
    private String periodName;
    private String periodStartDateStr;
    private String periodEndDateStr;

    /**
     * Поля статусу виконанння в SharePiont
     */
    private static final String INC_EXP_STATUS = "OData__x041d__x0430__x0447__x0438__x04";

    /**
     * Статуси виконання
     */
    private String failStatus = "ОШИБКА";
    private String successStatus = "ДА";

    public IncExpFasade(String periodId, String periodName, String periodStartDateStr, String periodEndDateStr) {
        this.periodId = periodId;
        this.periodName = periodName;
        this.periodStartDateStr = periodStartDateStr;
        this.periodEndDateStr = periodEndDateStr;
    }

    public void run() {
        SharePointUpdater updater = new SharePointUpdater(successStatus, failStatus, periodId);
        co.maxbi.logic.workflow.Accrualslnc accrualsInc = new co.maxbi.logic.workflow.Accrualslnc();

        try {
            accrualsInc.calcAccrualslnc(periodId, periodName, periodStartDateStr, periodEndDateStr);
            logger.info("Successful closed Income!");
            // updater.sendStatusOK(INC_EXP_STATUS);
        } catch (SQLException e) {
            //   updater.sendStatusFAIL(INC_EXP_STATUS);
            logger.error("SQL problem", e);
        } catch (NullPointerException e) {
            //   updater.sendStatusFAIL(INC_EXP_STATUS);
            logger.error("NullPointerException", e);
        } catch (Exception e) {
            //    updater.sendStatusFAIL(INC_EXP_STATUS);
            logger.error("Exception", e);
        }


    }


}
