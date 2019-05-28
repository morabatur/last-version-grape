package co.maxbi.rest.entity.dictionary;

import com.google.gson.annotations.SerializedName;

public class GroupInfo {
    @SerializedName("Title")
    private String title;

    public GroupInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "title='" + title + '\'' +
                '}';
    }
}
