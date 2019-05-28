package co.maxbi.db.pojo.local;

import java.util.Date;

public class ClosedExpensesPeriods {
    private double periodExpenseAcc;
    private int durationPeriodAcc;
    private Date startMin;

    public ClosedExpensesPeriods(double periodExpenseAcc, int durationPeriodAcc, Date startMin) {
        this.periodExpenseAcc = periodExpenseAcc;
        this.durationPeriodAcc = durationPeriodAcc;
        this.startMin = startMin;
    }


    public double getPeriodExpenseAcc() {
        return periodExpenseAcc;
    }

    public void setPeriodExpenseAcc(double periodExpenseAcc) {
        this.periodExpenseAcc = periodExpenseAcc;
    }

    public int getDurationPeriodAcc() {
        return durationPeriodAcc;
    }

    public void setDurationPeriodAcc(int durationPeriodAcc) {
        this.durationPeriodAcc = durationPeriodAcc;
    }

    public Date getStartMin() {
        return startMin;
    }

    public void setStartMin(Date startMin) {
        this.startMin = startMin;
    }


    @Override
    public String toString() {
        return "ClosedExpensesPeriods{" +
                "periodExpenseAcc=" + periodExpenseAcc +
                ", durationPeriodAcc=" + durationPeriodAcc +
                ", startMin=" + startMin +
                '}';
    }
}



