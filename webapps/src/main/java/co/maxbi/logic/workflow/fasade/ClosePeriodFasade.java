package co.maxbi.logic.workflow.fasade;

import org.apache.log4j.Logger;

public class ClosePeriodFasade implements Runnable {
    final static Logger logger = Logger.getLogger(ClosePeriodFasade.class);

    private String periodId;
    private String periodName;
    private String periodStartDateStr;
    private String periodEndDateStr;

    public ClosePeriodFasade(String periodId, String periodName, String periodStartDateStr, String periodEndDateStr) {
        this.periodId = periodId;
        this.periodName = periodName;
        this.periodStartDateStr = periodStartDateStr;
        this.periodEndDateStr = periodEndDateStr;
    }

    @Override
    public void run() {
        new IncExpFasade(periodId, periodName, periodStartDateStr, periodEndDateStr).run();
        new AddExpFasade(periodId, periodName, periodStartDateStr, periodEndDateStr).run();
        new ExpShiftFasade(periodId, periodName, periodStartDateStr, periodEndDateStr).run();
    }
}
