package co.maxbi.db.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Клас-конектор для з'єднання з БД
 */
public class DatabaseConnector {

    private static final Logger log = Logger.getLogger(DatabaseConnector.class);

    private static final String HOST_NAME = "fintest.database.windows.net";
    private static final String DB_NAME = "fintest";
    private static final String DB_USER = "mityamihailov";
    private static final String PASSWORD = "8qS,YZYB(e\\4V%=$";
    private static final String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", HOST_NAME, DB_NAME, DB_USER, PASSWORD);

    /**
     * Метод дозволяє встановити з'єднання з базою даних
     *
     * @return Connection
     */

    public Connection conect() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            log.debug("Cant register driver class", e);
        }


        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL);

            log.info("Successful connection to the database");
        } catch (SQLException ex) {
            log.debug("Cant connect to database. Exception: ", ex);
        }

        return connection;
    }
}
