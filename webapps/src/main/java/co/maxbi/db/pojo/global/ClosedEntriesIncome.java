package co.maxbi.db.pojo.global;


public class ClosedEntriesIncome {

  private double id;
  private double totalFixSum;
  private java.sql.Timestamp fixStart;
  private java.sql.Timestamp fixEnd;
  private double duration;
  private double durationInPeriod;
  private double durationPeriodAcc;
  private double periodSum;
  private double projectId;
  private double agAgFix;
  private double dcFix;
  private double consFix;
  private double periodAgAg;
  private double periodDc;
  private double periodCons;
  private double dncAgAgFix;
  private double dncConsFix;
  private double dncDcFix;
  private double periodId;
  private String periodName;
  private java.sql.Timestamp periodStart;
  private java.sql.Timestamp periodEnd;
  private String periodState;
  private String periodStartStr;
  private String periodEndStr;


  public double getId() {
    return id;
  }

  public void setId(double id) {
    this.id = id;
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


  public double getProjectId() {
    return projectId;
  }

  public void setProjectId(double projectId) {
    this.projectId = projectId;
  }


  public double getAgAgFix() {
    return agAgFix;
  }

  public void setAgAgFix(double agAgFix) {
    this.agAgFix = agAgFix;
  }


  public double getDcFix() {
    return dcFix;
  }

  public void setDcFix(double dcFix) {
    this.dcFix = dcFix;
  }


  public double getConsFix() {
    return consFix;
  }

  public void setConsFix(double consFix) {
    this.consFix = consFix;
  }


  public double getPeriodAgAg() {
    return periodAgAg;
  }

  public void setPeriodAgAg(double periodAgAg) {
    this.periodAgAg = periodAgAg;
  }


  public double getPeriodDc() {
    return periodDc;
  }

  public void setPeriodDc(double periodDc) {
    this.periodDc = periodDc;
  }


  public double getPeriodCons() {
    return periodCons;
  }

  public void setPeriodCons(double periodCons) {
    this.periodCons = periodCons;
  }


  public double getDncAgAgFix() {
    return dncAgAgFix;
  }

  public void setDncAgAgFix(double dncAgAgFix) {
    this.dncAgAgFix = dncAgAgFix;
  }


  public double getDncConsFix() {
    return dncConsFix;
  }

  public void setDncConsFix(double dncConsFix) {
    this.dncConsFix = dncConsFix;
  }


  public double getDncDcFix() {
    return dncDcFix;
  }

  public void setDncDcFix(double dncDcFix) {
    this.dncDcFix = dncDcFix;
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


  @Override
  public String toString() {
    return "ClosedEntriesIncome{" +
            "id=" + id +
            ", totalFixSum=" + totalFixSum +
            ", fixStart=" + fixStart +
            ", fixEnd=" + fixEnd +
            ", duration=" + duration +
            ", durationInPeriod=" + durationInPeriod +
            ", durationPeriodAcc=" + durationPeriodAcc +
            ", periodSum=" + periodSum +
            ", projectId=" + projectId +
            ", agAgFix=" + agAgFix +
            ", dcFix=" + dcFix +
            ", consFix=" + consFix +
            ", periodAgAg=" + periodAgAg +
            ", periodDc=" + periodDc +
            ", periodCons=" + periodCons +
            ", dncAgAgFix=" + dncAgAgFix +
            ", dncConsFix=" + dncConsFix +
            ", dncDcFix=" + dncDcFix +
            ", periodId=" + periodId +
            ", periodName='" + periodName + '\'' +
            ", periodStart=" + periodStart +
            ", periodEnd=" + periodEnd +
            ", periodState='" + periodState + '\'' +
            ", periodStartStr='" + periodStartStr + '\'' +
            ", periodEndStr='" + periodEndStr + '\'' +
            '}';
  }
}
