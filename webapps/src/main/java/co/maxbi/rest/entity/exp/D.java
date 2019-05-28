package co.maxbi.rest.entity.exp;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class D {
    private Result results[];
    @SerializedName("__next")
    private String nextJson;

    public D() {
    }

    public D(Result[] results, String __next) {
        this.results = results;
        this.nextJson = __next;
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
        return "D{" +
                "results=" + Arrays.toString(results) +
                ", nextJson='" + nextJson + '\'' +
                '}';
    }
}

