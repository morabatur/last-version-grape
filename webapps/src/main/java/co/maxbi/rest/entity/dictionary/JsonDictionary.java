package co.maxbi.rest.entity.dictionary;

public class JsonDictionary {
    private DataDictionary d;


    public JsonDictionary() {
    }

    public JsonDictionary(DataDictionary d) {
        this.d = d;
    }

    public DataDictionary getD() {
        return d;
    }

    public void setD(DataDictionary d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "JsonDictionary{" +
                "d=" + d +
                '}';
    }
}
