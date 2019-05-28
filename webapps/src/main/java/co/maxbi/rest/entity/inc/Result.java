package co.maxbi.rest.entity.inc;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Result {
    private int ID;
    private String Title;
    @SerializedName("OData__x0411__x044e__x0434__x0436__x04")
    private double sum;//сумма
    @SerializedName("AgAg_x0020__x0432__x0020__x0440_")
    private double agAg;//AgAg
    @SerializedName("Dc_x0020__x0432__x0020__x0440__x")
    private double dc;//Dc
    @SerializedName("Cons_x0020__x0432__x0020__x0440_")
    private double cons;//Cons
    @SerializedName("OData__x041d__x0430__x0447__x0430__x04")
    private Date dateOfStartProject;//начало проекта
    @SerializedName("OData__x041e__x043a__x043e__x043d__x04")
    private Date dateOfEndProject;//конец проекта
    @SerializedName("AgAg_x0020__x0028_actual_x0029_")
    private double agAgActual;//AgAgActual
    @SerializedName("Cons_x0020__x0028_actual_x0029_")
    private double consActual;//ConsActual
    @SerializedName("Dc_x0020__x0028_actual_x0029_")
    private double dcActual;//DcActual


    public Result() {

    }

    public Result(int ID, String title, double sum, double agAg, double dc, double cons, Date dateOfStartProject, Date dateOfEndProject, double agAgActual, double consActual, double dcActual) {
        this.ID = ID;
        Title = title;
        this.sum = sum;
        this.agAg = agAg;
        this.dc = dc;
        this.cons = cons;
        this.dateOfStartProject = dateOfStartProject;
        this.dateOfEndProject = dateOfEndProject;
        this.agAgActual = agAgActual;
        this.consActual = consActual;
        this.dcActual = dcActual;
    }


    public Date getDateOfStartProject() {
        return dateOfStartProject;
    }

    public void setDateOfStartProject(Date dateOfStartProject) {
        this.dateOfStartProject = dateOfStartProject;
    }

    public Date getDateOfEndProject() {
        return dateOfEndProject;
    }

    public void setDateOfEndProject(Date dateOfEndProject) {
        this.dateOfEndProject = dateOfEndProject;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAgAg() {
        return agAg;
    }

    public void setAgAg(double agAg) {
        this.agAg = agAg;
    }

    public double getDc() {
        return dc;
    }

    public void setDc(double dc) {
        this.dc = dc;
    }

    public double getCons() {
        return cons;
    }

    public void setCons(double cons) {
        this.cons = cons;
    }

    public double getAgAgActual() {
        return agAgActual;
    }

    public void setAgAgActual(double agAgActual) {
        this.agAgActual = agAgActual;
    }

    public double getConsActual() {
        return consActual;
    }

    public void setConsActual(double consActual) {
        this.consActual = consActual;
    }

    public double getDcActual() {
        return dcActual;
    }

    public void setDcActual(double dcActual) {
        this.dcActual = dcActual;
    }



    @Override
    public String toString() {
        return "Result{" +
                "ID=" + ID +
                ", Title='" + Title + '\'' +
                ", sum=" + sum +
                ", agAg=" + agAg +
                ", dc=" + dc +
                ", cons=" + cons +
                ", dateOfStartProject='" + dateOfStartProject + '\'' +
                ", dateOfEndProject='" + dateOfEndProject + '\'' +
                ", agAgActual=" + agAgActual +
                ", consActual=" + consActual +
                ", dcActual=" + dcActual +
                '}';
    }
}
