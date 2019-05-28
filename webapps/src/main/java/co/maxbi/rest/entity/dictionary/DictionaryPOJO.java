package co.maxbi.rest.entity.dictionary;

public class DictionaryPOJO {
    private String title;
    private int groupId;

    public DictionaryPOJO() {
    }

    public DictionaryPOJO(String title, int groupId) {
        this.title = title;
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
