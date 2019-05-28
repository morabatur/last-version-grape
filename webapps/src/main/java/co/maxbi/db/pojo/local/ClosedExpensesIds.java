package co.maxbi.db.pojo.local;

public class ClosedExpensesIds {
    private int expensesId;
    private int groupId;
    private int projectId;


    public ClosedExpensesIds(int expensesId, int groupId, int projectId) {
        this.expensesId = expensesId;
        this.groupId = groupId;
        this.projectId = projectId;
    }


    public int getExpensesId() {
        return expensesId;
    }

    public void setExpensesId(int expensesId) {
        this.expensesId = expensesId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    @Override
    public String toString() {
        return "ClosedExpensesIds{" +
                "expensesId=" + expensesId +
                ", groupId=" + groupId +
                ", projectId=" + projectId +
                '}';
    }
}
