package co.maxbi.rest.entity.dictionary;

import com.google.gson.annotations.SerializedName;

public class ResultDictionary {

    @SerializedName("OData__x0413__x0440__x0443__x043f__x04")
    private GroupInfo data;
    @SerializedName("Id")
    private int categoryId;
    @SerializedName("OData__x0413__x0440__x0443__x043f__x04Id")
    private int groupId;

    public ResultDictionary(GroupInfo data, int categoryId, int groupId) {
        this.data = data;
        this.categoryId = categoryId;
        this.groupId = groupId;
    }

    public GroupInfo getData() {
        return data;
    }

    public void setData(GroupInfo data) {
        this.data = data;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


    @Override
    public String toString() {
        return "ResultDictionary{" +
                "data=" + data +
                ", categoryId=" + categoryId +
                ", groupId=" + groupId +
                '}';
    }
}
