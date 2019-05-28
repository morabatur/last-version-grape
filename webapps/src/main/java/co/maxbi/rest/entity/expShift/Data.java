package co.maxbi.rest.entity.expShift;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Data {
    private Result results[];
    @SerializedName("__next")
    private String nextJson;

    public Data() {
    }

    public Data(Result[] results, String nextJson) {
        this.results = results;
        this.nextJson = nextJson;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public String getNextJson() {
        return nextJson;
    }

    public void setNextJson(String nextJson) {
        this.nextJson = nextJson;
    }

    @Override
    public String toString() {
        return "Data{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
