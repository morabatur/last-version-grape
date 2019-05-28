package co.maxbi.rest.entity.addExp;

import com.google.gson.annotations.SerializedName;

public class JsonDataAddExp {
    @SerializedName("d")
    private Data data;


    public JsonDataAddExp() {
    }

    public JsonDataAddExp(Data data) {
        this.data = data;
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "JsonDataAddExp{" +
                "data=" + data +
                '}';
    }
}
