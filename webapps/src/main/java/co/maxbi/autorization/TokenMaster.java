package co.maxbi.autorization;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton, що дозволяє отримувати завжди акутальний токен
 */
public class TokenMaster {
    private static final Logger log = Logger.getLogger(TokenMaster.class);

    private static TokenMaster masterToken;
    private static final String TOKEN_MASTER_URL = "https://accounts.accesscontrol.windows.net/6a0102a6-14b8-44ff-b0bb-9700aad06eea/tokens/OAuth/2";
    private static final long TOKEN_LIVE_TIME = 3300000;//55 хв
    private static long tokenStartDate;
    private static String tokenKey = "";

    private TokenMaster() {
    }

    /**
     * Створення єдиного об'єкту класу
     *
     * @return
     */
    public static TokenMaster create() {
        if (masterToken == null) {
            masterToken = new TokenMaster();
        }

        return masterToken;
    }

    /**
     * Метод повертає текстове значення токену
     *
     * @return
     */
    public String getTokenKey() {

        if (tokenKey == null || tokenKey.isEmpty()) {//якщо токена ще немає або він пустий - створити його
            tokenKey = tokenKeyRequest();//отримати токен
            return tokenKey;
        } else if (isLiveToken()) {//інакше перевірити час життя токену
            return tokenKey;
        } else {
            tokenKey = tokenKeyRequest();
            return tokenKey;
        }

    }

    /**
     * Метод для отримання токену за запитом з набором певних мараметрів
     *
     * @return
     */
    public String tokenKeyRequest() {
        String tokenKey = "";
        tokenStartDate = System.currentTimeMillis(); // зазаначити дату створення токена

        try {
            TokenDistributor distributor = new TokenDistributor(TOKEN_MASTER_URL);
            String tokenData = distributor.reauestToken("POST", parametersMap());//Отримати токен в JSON
            tokenKey = TokenJsonParser.getTokenKey(tokenData);//Розпарсити і отримати лише ключ
        } catch (IOException ex) {
            log.debug("Fail send token request", ex);
        }

        return tokenKey;
    }

    /**
     * Метод перевіряє чи не минув ще час діїї токену
     *
     * @return
     */
    private boolean isLiveToken() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - tokenStartDate) > TOKEN_LIVE_TIME) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Map з параметрами які потрібно передати для відправлення запиту
     *
     * @return
     */
    private Map parametersMap() {
        String garantType = "client_credentials";
        String clientId = "9d3ac66a-5c4c-473a-96cd-b4f9cd559e99@6a0102a6-14b8-44ff-b0bb-9700aad06eea";
        String clientSecret = "zgPBMsRUC8TfMjAIKOijcZxp695kPE4Tv2nudqPe3xg=";
        String resource = "00000003-0000-0ff1-ce00-000000000000/possiblegroup.sharepoint.com@6a0102a6-14b8-44ff-b0bb-9700aad06eea";

        Map<String, String> mapWithParameter = new HashMap<>();
        mapWithParameter.put("grant_type", garantType);
        mapWithParameter.put("client_id", clientId);
        mapWithParameter.put("charset", "utf-8");
        mapWithParameter.put("client_secret", clientSecret);
        mapWithParameter.put("resource", resource);

        return mapWithParameter;
    }


}
