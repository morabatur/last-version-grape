package co.maxbi.autorization;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.Map;


/**
 * Клас, що поставляє токен, отримуючи його за певною адресою
 */
public class TokenDistributor {
    private static final Logger log = Logger.getLogger(TokenDistributor.class);
    private String tokenDistributorUrl;

    public TokenDistributor(String tokenMaster) {
        this.tokenDistributorUrl = tokenMaster;

    }

    /**
     * Метод відправляє запит на отримання токену
     *
     * @param method
     * @return InputStream
     * @throws IOException
     */
    public String reauestToken(String method, Map parametrMap) throws IOException {
        HttpURLConnection connection = createConnection(method);

        OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
        streamWriter.write(transformParam(parametrMap));
        streamWriter.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
        String result = reader.readLine();

        log.info("Tokent answer: " + result);
        return result;

    }

    /**
     * Метод створює з'єднання з постачальником токенів доступу
     *
     * @param method
     * @return
     */
    private HttpURLConnection createConnection(String method) {
        URL url = null;
        HttpURLConnection connection = null;

        try {
            //Створити з'єднання
            url = new URL(tokenDistributorUrl);
            connection = (HttpURLConnection) url.openConnection();
            //Задати налаштування з'єднання
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
        } catch (MalformedURLException ex) {
            log.debug(ex.getMessage());
        } catch (ProtocolException ex) {
            log.debug(ex.getMessage());
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }

        return connection;
    }

    /**
     * Метод для перетворення вхідних параметрів в підходящий для POST запиту вигляд
     *
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    private String transformParam(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


    public String getTokenDistributorUrl() {
        return tokenDistributorUrl;
    }

    public void setTokenDistributorUrl(String tokenDistributorUrl) {
        this.tokenDistributorUrl = tokenDistributorUrl;
    }

}
