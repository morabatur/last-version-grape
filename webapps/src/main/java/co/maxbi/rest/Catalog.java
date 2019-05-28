package co.maxbi.rest;


import co.maxbi.autorization.TokenMaster;
import co.maxbi.rest.entity.dictionary.DictionaryPOJO;
import co.maxbi.rest.entity.dictionary.JsonDictionary;
import co.maxbi.rest.entity.dictionary.ResultDictionary;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton клас, що тримає в пам'яті актуальний довідник з категоріями та групами проектів
 */
public class Catalog {
    private static final Logger log = Logger.getLogger(Catalog.class.getName());

    private static Catalog catalogue;
    private static Map<Integer, DictionaryPOJO> mapDictionary;


    private Catalog() {
        mapDictionary = createMap();
    }

    public static Catalog getInstance() {
        if (catalogue == null) {
            catalogue = new Catalog();
            return catalogue;
        }

        return catalogue;
    }

    public Map<Integer, DictionaryPOJO> getMapDictionary() {
        return mapDictionary;
    }

    private Map<Integer, DictionaryPOJO> createMap() {
        TokenMaster tokenMaster = TokenMaster.create();
        DataLoader jsonDataLoader = new DataLoader(tokenMaster);
        Parser parserJsonData = new Parser();
        JsonReader reader = null;
        try {
            reader = parserJsonData.dataFromJson(jsonDataLoader.requestJSONData("https://possiblegroup.sharepoint.com/sites/fintest/_vti_bin/client.svc/web/lists/getbyid(guid'1789DE7F-4B81-44E3-8402-8EC7856174D5')/Items?$select=Id,OData__x0413__x0440__x0443__x043f__x04Id,OData__x0413__x0440__x0443__x043f__x04/Title&$expand=OData__x0413__x0440__x0443__x043f__x04"));
        } catch (IOException e) {
            log.debug("Cant load data", e);
        }
        JsonDictionary jsonDictionary = parserJsonData.createGson().fromJson(reader, JsonDictionary.class);

        Map<Integer, DictionaryPOJO> mapDictionary = new HashMap();

        for (ResultDictionary result : jsonDictionary.getD().getResults()) {
            mapDictionary.put(result.getCategoryId(), new DictionaryPOJO(result.getData().getTitle(), result.getGroupId()));
        }

        return mapDictionary;

    }

}
