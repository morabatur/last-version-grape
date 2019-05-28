package co.maxbi.rest.entity.addExp;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Result {
    @SerializedName("Id")
    private int idExp;
    @SerializedName("OData__x0421__x0443__x043c__x043c__x04")
    private double sum;
    @SerializedName("OData__x041e__x043a__x043e__x043d__x04")
    private Date workEnd;
    @SerializedName("OData__x041f__x0440__x043e__x0435__x04")
    private Income incomeInfo;
    @SerializedName("OData__x0422__x0438__x043f__x0020_2_x0Id")
    private int idCategory;

    public Result() {
    }

    public Result(int id, double sum, Date workEnd, Income incomeInfo, int idCategory) {
        this.idExp = id;
        this.sum = sum;
        this.workEnd = workEnd;
        this.incomeInfo = incomeInfo;
        this.idCategory = idCategory;
    }


    public int getIdExp() {
        return idExp;
    }

    public void setIdExp(int idExp) {
        this.idExp = idExp;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(Date workEnd) {
        this.workEnd = workEnd;
    }

    public Income getIncomeInfo() {
        return incomeInfo;
    }

    public void setIncomeInfo(Income incomeInfo) {
        this.incomeInfo = incomeInfo;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }


    @Override
    public String toString() {
        return "Result{" +
                "idExp=" + idExp +
                ", sum=" + sum +
                ", workEnd=" + workEnd +
                ", incomeInfo=" + incomeInfo +
                ", idCategory=" + idCategory +
                '}';
    }
}
