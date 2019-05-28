package co.maxbi.rest.entity.dictionary;

import java.util.Arrays;

public class DataDictionary {
    private ResultDictionary results[];


    public DataDictionary(ResultDictionary[] results) {
        this.results = results;
    }

    public ResultDictionary[] getResults() {
        return results;
    }

    public void setResults(ResultDictionary[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
