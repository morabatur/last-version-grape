package co.maxbi.autorization;

import co.maxbi.autorization.entity.Token;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Клас для роботи парсингом JSON, що містить в собі токен
 */
public class TokenJsonParser {


    public static String getTokenKey(String  JSONToken){
        Gson parser = new Gson();
        JsonReader jsonReader = new JsonReader(new StringReader(JSONToken));
        Token token =  parser.fromJson(jsonReader, Token.class);

        return token.getAccessToken();
    }


}
