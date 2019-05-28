package co.maxbi.db.pojo.global;


public class ClosedEntriesExpenses {

  private double id;
  private double expenseId;
  private double totalFixSum;
  private java.sql.Timestamp fixStart;
  private java.sql.Timestamp fixEnd;
  private double duration;
  private double durationInPeriod;
  private double durationPeriodAcc;
  private double periodSum;
  private double fixedExpProjId;
  private String corrClosed;
  private String shifted;
  private double periodId;
  private String periodName;
  private java.sql.Timestamp periodStart;
  private java.sql.Timestamp periodEnd;
  private String periodState;
  private String periodStartStr;
  private String periodEndStr;
  private Double fixedExpCategory_GroupId;
  private String fixedExpCategory_GroupName;


  public double getId() {
    return id;
  }

  public void setId(double id) {
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


  public java.sql.Timestamp getFixStart() {
    return fixStart;
  }

  public void setFixStart(java.sql.Timestamp fixStart) {
    this.fixStart = fixStart;
  }


  public java.sql.Timestamp getFixEnd() {
    return fixEnd;
  }

  public void setFixEnd(java.sql.Timestamp fixEnd) {
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


  public String getCorrClosed() {
    return corrClosed;
  }

  public void setCorrClosed(String corrClosed) {
    this.corrClosed = corrClosed;
  }


  public String getShifted() {
    return shifted;
  }

  public void setShifted(String shifted) {
    this.shifted = shifted;
  }


  public double getPeriodId() {
    return periodId;
  }

  public void setPeriodId(double periodId) {
    this.periodId = periodId;
  }


  public String getPeriodName() {
    return periodName;
  }

  public void setPeriodName(String periodName) {
    this.periodName = periodName;
  }


  public java.sql.Timestamp getPeriodStart() {
    return periodStart;
  }

  public void setPeriodStart(java.sql.Timestamp periodStart) {
    this.periodStart = periodStart;
  }


  public java.sql.Timestamp getPeriodEnd() {
    return periodEnd;
  }

  public void setPeriodEnd(java.sql.Timestamp periodEnd) {
    this.periodEnd = periodEnd;
  }


  public String getPeriodState() {
    return periodState;
  }

  public void setPeriodState(String periodState) {
    this.periodState = periodState;
  }


  public String getPeriodStartStr() {
    return periodStartStr;
  }

  public void setPeriodStartStr(String periodStartStr) {
    this.periodStartStr = periodStartStr;
  }


  public String getPeriodEndStr() {
    return periodEndStr;
  }

  public void setPeriodEndStr(String periodEndStr) {
    this.periodEndStr = periodEndStr;
  }


  public Double getFixedExpCategory_GroupId() {
    return fixedExpCategory_GroupId;
  }

  public void setFixedExpCategory_GroupId(Double fixedExpCategory_GroupId) {
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
            ", corrClosed='" + corrClosed + '\'' +
            ", shifted='" + shifted + '\'' +
            ", periodId=" + periodId +
            ", periodName='" + periodName + '\'' +
            ", periodStart=" + periodStart +
            ", periodEnd=" + periodEnd +
            ", periodState='" + periodState + '\'' +
            ", periodStartStr='" + periodStartStr + '\'' +
            ", periodEndStr='" + periodEndStr + '\'' +
            ", fixedExpCategory_GroupId=" + fixedExpCategory_GroupId +
            ", fixedExpCategory_GroupName='" + fixedExpCategory_GroupName + '\'' +
            '}';
  }
}
