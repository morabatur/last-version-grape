package co.maxbi.rest.entity.exp;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("ID")
    private int idExp;
    @SerializedName("OData__x0421__x0443__x043c__x043c__x04")
    private double sumExp;
    @SerializedName("OData__x0422__x0438__x043f__x0020_2_x0")
    private IdResult idCategory;
    @SerializedName("OData__x041f__x0440__x043e__x0435__x04Id")
    private int idInc;

    public Result() {
    }

    public Result(int idExp, double sumExp, IdResult idCategory, int idInc) {
        this.idExp = idExp;
        this.sumExp = sumExp;
        this.idCategory = idCategory;
        this.idInc = idInc;
    }

    public int getIdInc() {
        return idInc;
    }

    public void setIdInc(int idInc) {
        this.idInc = idInc;
    }

    public int getIdExp() {
        return idExp;
    }

    public void setIdExp(int idExp) {
        this.idExp = idExp;
    }

    public double getSumExp() {
        return sumExp;
    }

    public void setSumExp(double sumExp) {
        this.sumExp = sumExp;
    }

    public IdResult getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(IdResult idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Result{" +
                "idExp=" + idExp +
                ", sumExp=" + sumExp +
                ", idCategory=" + idCategory +
                ", idInc=" + idInc +
                '}';
    }
}


