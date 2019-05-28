package co.maxbi.rest.entity.expShift;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("OData__x0422__x0438__x043f__x0020_2_x0Id")
    private int categoryId;
    @SerializedName("OData__x041f__x0440__x043e__x0435__x04Id")
    private int projectId;

    public Result() {
    }

    public Result(int categoryId, int projectId) {
        this.categoryId = categoryId;
        this.projectId = projectId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "categoryId=" + categoryId +
                ", projectId=" + projectId +
                '}';
    }
}
