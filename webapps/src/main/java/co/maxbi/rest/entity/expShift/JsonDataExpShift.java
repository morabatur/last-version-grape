package co.maxbi.rest.entity.expShift;

import com.google.gson.annotations.SerializedName;

public class JsonDataExpShift {
    @SerializedName("d")
    private Data data;

    public JsonDataExpShift() {
    }

    public JsonDataExpShift(Data data) {
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
        return "JsonDataExpShift{" +
                "data=" + data +
                '}';
    }
}
