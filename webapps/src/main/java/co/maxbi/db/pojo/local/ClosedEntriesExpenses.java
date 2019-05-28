package co.maxbi.db.pojo.local;


import java.util.Date;

public class ClosedEntriesExpenses {
    private int id;
    private double expenseId;
    private double totalFixSum;
    private Date fixStart;
    private Date fixEnd;
    private double duration;
    private double durationInPeriod;
    private double durationPeriodAcc;
    private double periodSum;
    private double fixedExpProjId;
    private String periodEndStr;
    private double fixedExpCategory_GroupId;
    private String fixedExpCategory_GroupName;


    public ClosedEntriesExpenses(int id, double expenseId, double totalFixSum, Date fixStart, Date fixEnd, double duration, double durationInPeriod, double durationPeriodAcc, double periodSum, double fixedExpProjId, String periodEndStr, double fixedExpCategory_GroupId, String fixedExpCategory_GroupName) {
        this.id = id;
        this.expenseId = expenseId;
        this.totalFixSum = totalFixSum;
        this.fixStart = fixStart;
        this.fixEnd = fixEnd;
        this.duration = duration;
        this.durationInPeriod = durationInPeriod;
        this.durationPeriodAcc = durationPeriodAcc;
        this.periodSum = periodSum;
        this.fixedExpProjId = fixedExpProjId;
        this.periodEndStr = periodEndStr;
        this.fixedExpCategory_GroupId = fixedExpCategory_GroupId;
        this.fixedExpCategory_GroupName = fixedExpCategory_GroupName;
    }


    public ClosedEntriesExpenses() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(double expenseId) {
        this.expenseId = expenseId;
    }

    public double getTotalFixSum() {
        return totalFixSum;
    }

    public void setTotalFixSum(double totalFixSum) {
        this.totalFixSum = totalFixSum;
    }

    public Date getFixStart() {
        return fixStart;
    }

    public void setFixStart(Date fixStart) {
        this.fixStart = fixStart;
    }

    public Date getFixEnd() {
        return fixEnd;
    }

    public void setFixEnd(Date fixEnd) {
        this.fixEnd = fixEnd;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDurationInPeriod() {
        return durationInPeriod;
    }

    public void setDurationInPeriod(double durationInPeriod) {
        this.durationInPeriod = durationInPeriod;
    }

    public double getDurationPeriodAcc() {
        return durationPeriodAcc;
    }

    public void setDurationPeriodAcc(double durationPeriodAcc) {
        this.durationPeriodAcc = durationPeriodAcc;
    }

    public double getPeriodSum() {
        return periodSum;
    }

    public void setPeriodSum(double periodSum) {
        this.periodSum = periodSum;
    }

    public double getFixedExpProjId() {
        return fixedExpProjId;
    }

    public void setFixedExpProjId(double fixedExpProjId) {
        this.fixedExpProjId = fixedExpProjId;
    }

    public String getPeriodEndStr() {
        return periodEndStr;
    }

    public void setPeriodEndStr(String periodEndStr) {
        this.periodEndStr = periodEndStr;
    }

    public double getFixedExpCategory_GroupId() {
        return fixedExpCategory_GroupId;
    }

    public void setFixedExpCategory_GroupId(double fixedExpCategory_GroupId) {
        this.fixedExpCategory_GroupId = fixedExpCategory_GroupId;
    }

    public String getFixedExpCategory_GroupName() {
        return fixedExpCategory_GroupName;
    }

    public void setFixedExpCategory_GroupName(String fixedExpCategory_GroupName) {
        this.fixedExpCategory_GroupName = fixedExpCategory_GroupName;
    }


    @Override
    public String toString() {
        return "ClosedEntriesExpenses{" +
                "id=" + id +
                ", expenseId=" + expenseId +
                ", totalFixSum=" + totalFixSum +
                ", fixStart=" + fixStart +
                ", fixEnd=" + fixEnd +
                ", duration=" + duration +
                ", durationInPeriod=" + durationInPeriod +
                ", durationPeriodAcc=" + durationPeriodAcc +
                ", periodSum=" + periodSum +
                ", fixedExpProjId=" + fixedExpProjId +
                ", periodEndStr='" + periodEndStr + '\'' +
                ", fixedExpCategory_GroupId=" + fixedExpCategory_GroupId +
                ", fixedExpCategory_GroupName='" + fixedExpCategory_GroupName + '\'' +
                '}';
    }
}
