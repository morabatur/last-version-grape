package co.maxbi.db.pojo.local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClosedIncomeSums {
    private Date minPeriodStart;
    private double SumDurationInPeriod;
    private double SumPeriodSum;
    private double SumPeriodAgAg;
    private double SumPeriodDc;
    private double SumPeriodCons;
    private double SumDncAgAgFix;
    private double SumDncConsFix;
    private double SumDncDcFix;

    public ClosedIncomeSums() {
    }

    public ClosedIncomeSums(String minPeriodStart, double sumDurationInPeriod, double sumPeriodSum, double sumPeriodAgAg, double sumPeriodDc, double sumPeriodCons, double sumDncAgAgFix, double sumDncConsFix, double sumDncDcFix) {
        try {
            this.minPeriodStart =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(minPeriodStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SumDurationInPeriod = sumDurationInPeriod;
        SumPeriodSum = sumPeriodSum;
        SumPeriodAgAg = sumPeriodAgAg;
        SumPeriodDc = sumPeriodDc;
        SumPeriodCons = sumPeriodCons;
        SumDncAgAgFix = sumDncAgAgFix;
        SumDncConsFix = sumDncConsFix;
        SumDncDcFix = sumDncDcFix;
    }

    public Date getMinPeriodStart() {
        return minPeriodStart;
    }

    public void setMinPeriodStart(String minPeriodStart) {
        try {
            this.minPeriodStart =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(minPeriodStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public double getSumDurationInPeriod() {
        return SumDurationInPeriod;
    }

    public void setSumDurationInPeriod(double sumDurationInPeriod) {
        SumDurationInPeriod = sumDurationInPeriod;
    }

    public double getSumPeriodSum() {
        return SumPeriodSum;
    }

    public void setSumPeriodSum(double sumPeriodSum) {
        SumPeriodSum = sumPeriodSum;
    }

    public double getSumPeriodAgAg() {
        return SumPeriodAgAg;
    }

    public void setSumPeriodAgAg(double sumPeriodAgAg) {
        SumPeriodAgAg = sumPeriodAgAg;
    }

    public double getSumPeriodDc() {
        return SumPeriodDc;
    }

    public void setSumPeriodDc(double sumPeriodDc) {
        SumPeriodDc = sumPeriodDc;
    }

    public double getSumPeriodCons() {
        return SumPeriodCons;
    }

    public void setSumPeriodCons(double sumPeriodCons) {
        SumPeriodCons = sumPeriodCons;
    }

    public double getSumDncAgAgFix() {
        return SumDncAgAgFix;
    }

    public void setSumDncAgAgFix(double sumDncAgAgFix) {
        SumDncAgAgFix = sumDncAgAgFix;
    }

    public double getSumDncConsFix() {
        return SumDncConsFix;
    }

    public void setSumDncConsFix(double sumDncConsFix) {
        SumDncConsFix = sumDncConsFix;
    }

    public double getSumDncDcFix() {
        return SumDncDcFix;
    }

    public void setSumDncDcFix(double sumDncDcFix) {
        SumDncDcFix = sumDncDcFix;
    }

    @Override
    public String toString() {
        return "ClosedIncomeSums{" +
                "minPeriodStart=" + minPeriodStart +
                ", SumDurationInPeriod=" + SumDurationInPeriod +
                ", SumPeriodSum=" + SumPeriodSum +
                ", SumPeriodAgAg=" + SumPeriodAgAg +
                ", SumPeriodDc=" + SumPeriodDc +
                ", SumPeriodCons=" + SumPeriodCons +
                ", SumDncAgAgFix=" + SumDncAgAgFix +
                ", SumDncConsFix=" + SumDncConsFix +
                ", SumDncDcFix=" + SumDncDcFix +
                '}';
    }
}
