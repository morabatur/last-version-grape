package co.maxbi.rest;

import co.maxbi.autorization.TokenMaster;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class allow you download JSON data from link with some token
 */
public class DataLoader {
    private static final Logger log = Logger.getLogger(DataLoader.class.getName());

    private TokenMaster token;

    public DataLoader(TokenMaster token) {
        this.token = token;
    }

    /**
     * Метод отримує JSON по URL авторизуючись з допомогою токену
     *
     * @param link
     * @return
     * @throws IOException
     */
    public InputStream requestJSONData(String link) throws IOException {
        URL url;
        HttpURLConnection connection = null;

        try {
            //Create connection
            url = new URL(link);

           log.info("My url = " + link);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;odata=verbose");
            connection.setRequestProperty("Authorization", "Bearer " + token.getTokenKey());


        } catch (MalformedURLException ex) {
            log.log(Level.SEVERE, ex.getMessage());
        } catch (ProtocolException ex) {
            log.log(Level.SEVERE, ex.getMessage());
        }

        return connection.getInputStream();
    }


    /**
     * Метод дозволяє оновити поле в SharePoint
     *
     * @param periodId номер періоду
     * @param field    поле для оновлення
     * @param status   статус який потрыбно присвоїти полю
     * @return
     * @throws IOException
     */

    public int updateldInSP(String periodId, String field, String status) throws IOException {

        URL url = new URL(buildLink(periodId));//
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        byte[] out = buildJson(field, status).getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.setRequestProperty("Accept", "application/json;odata=verbose");
        http.setRequestProperty("Content-Type", "application/json;odata=verbose");
        http.setRequestProperty("X-HTTP-Method", "MERGE");
        http.setRequestProperty("IF-MATCH", "*");
        http.setRequestProperty("Authorization", "Bearer " + token.getTokenKey());
        http.connect();

        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }


        return http.getResponseCode();
    }


    private String buildLink(String periodId) {
        StringBuilder linkBuilder = new StringBuilder();
        linkBuilder.append("https://possiblegroup.sharepoint.com/sites/fintest/_api/web/lists/getbyid(guid'E6ED45E6-54BA-4B1D-AA83-23232E7B922D')/Items(");
        linkBuilder.append(periodId);
        linkBuilder.append(")");

        return linkBuilder.toString();
    }

    public String buildJson(String field, String status) {
        //Example JSON:
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{ '__metadata': { 'type': 'SP.Data.ListListItem' }, '");
        jsonBuilder.append(field);
        jsonBuilder.append("': '");
        jsonBuilder.append(status);
        jsonBuilder.append("'}");

        return jsonBuilder.toString();
    }

}
