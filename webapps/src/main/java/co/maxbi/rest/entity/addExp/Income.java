package co.maxbi.rest.entity.addExp;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Income {
    @SerializedName("ID")
    private int id;
    @SerializedName("OData__x041d__x0430__x0447__x0430__x04")
    private Date begin;
    @SerializedName("OData__x041e__x043a__x043e__x043d__x04")
    private Date endProject;


    public Income() {
    }

    public Income(int id, Date begin, Date endProject) {
        this.id = id;
        this.begin = begin;
        this.endProject = endProject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEndProject() {
        return endProject;
    }

    public void setEndProject(Date endProject) {
        this.endProject = endProject;
    }


    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", begin=" + begin +
                ", endProject=" + endProject +
                '}';
    }
}
