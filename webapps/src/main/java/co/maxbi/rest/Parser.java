package co.maxbi.rest;


import co.maxbi.converter.date.TimeZoneConverter;
import co.maxbi.rest.entity.inc.JsonDataAccrualsIncome;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Клас дозволяє парсити вхідні дані та зписувати їх у відповідні POJO
 */
public class Parser {

    public JsonDataAccrualsIncome dataFromJson(String fileName) throws FileNotFoundException {
        Gson gson = createGson();
        JsonReader reader = new JsonReader(new FileReader(fileName));//"src/main/resources/test.json"
        JsonDataAccrualsIncome data = gson.fromJson(reader, JsonDataAccrualsIncome.class);

        return data;

    }

    public JsonReader dataFromJson(InputStream jsonStream) {
        JsonReader reader = new JsonReader(new InputStreamReader(jsonStream));

        return reader;

    }

    /**
     * Метод створює особливий Gson, що дозволяє парсити дату у специфічному форматі по Грінвічу
     *
     * @return
     */
    public Gson createGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Date dt = java.sql.Timestamp.valueOf(TimeZoneConverter.convertInMoscowTime(json.getAsString()));

                return dt;
            }
        })
                .create();
        return gson;
    }
}
