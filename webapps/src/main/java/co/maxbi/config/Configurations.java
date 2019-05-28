package co.maxbi.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configurations {
    private static final String CONFIG_FILE_PATH = "/home/grape-config/config.json";

    private static String expensesTableName;
    private static String incomeTableName;

    public static String getExpensesTableName() {
        return expensesTableName;
    }

    public static void setExpensesTableName(String expensesTableName) {
        Configurations.expensesTableName = expensesTableName;
    }

    public static String getIncomeTableName() {
        return incomeTableName;
    }

    public static void setIncomeTableName(String incomeTableName) {
        Configurations.incomeTableName = incomeTableName;
    }


    public static void configFromFile() throws FileNotFoundException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_FILE_PATH));

        Gson gson = new Gson();
        JsonObject js = gson.fromJson(bufferedReader, JsonObject.class);
        expensesTableName = js.get("ClosedExpensesTable").getAsString();
        incomeTableName = js.get("ClosedIncomeTable").getAsString();
    }
}

