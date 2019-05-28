package co.maxbi.rest.entity.exp;

public class JsonDataAccrualsExp {
    private D d;

    public JsonDataAccrualsExp() {
    }

    public JsonDataAccrualsExp(D d) {
        this.d = d;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }


    @Override
    public String toString() {
        return "JsonDataAccrualsIncome{" +
                "d=" + d +
                '}';
    }
}
