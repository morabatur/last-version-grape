package co.maxbi.db.dao;

import co.maxbi.config.Configurations;
import co.maxbi.db.connector.DatabaseConnector;
import co.maxbi.db.pojo.global.ClosedEntriesIncome;
import co.maxbi.db.pojo.local.ClosedIncomeSums;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class DBRequestClosedEntriesIncome {
    private static String TABLE_NAME = Configurations.getIncomeTableName();// = "dbo.ClosedEntriesIncome";
    private static final Logger log = Logger.getLogger(DBRequestClosedEntriesIncome.class);

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static void setTableName(String tableName) {
        TABLE_NAME = tableName;
    }

    public ClosedIncomeSums selectClosedEntriesIncomeItems(String ID, String periodStart) {

        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectSql = "SELECT MIN(PeriodStart) AS MinPeriodStart, SUM(DurationInPeriod) AS SumDurationInPeriod, SUM(PeriodSum) AS SumPeriodSum,\n" +
                "       SUM(PeriodAgAg) AS SumPeriodAgAg, SUM(PeriodDc) AS SumPeriodDc, SUM(PeriodCons) AS SumPeriodCons,\n" +
                "       SUM(DncAgAgFix) AS SumDncAgAgFix, SUM(DncConsFix) AS SumDncConsFix, SUM(DncDcFix) AS SumDncDcFix\n" +
                "FROM " + TABLE_NAME + "\n" +
                "WHERE ProjectId = ? AND PeriodEnd  < ?";

        ClosedIncomeSums closedEntriesIncome = null;

        try {

            PreparedStatement statement = connection.prepareStatement(selectSql);

            statement.setString(1, ID);
            statement.setString(2, periodStart);


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                closedEntriesIncome = new ClosedIncomeSums();
                closedEntriesIncome.setMinPeriodStart(resultSet.getString(1));
                closedEntriesIncome.setSumDurationInPeriod(resultSet.getFloat(2));
                closedEntriesIncome.setSumPeriodSum(resultSet.getDouble(3));
                closedEntriesIncome.setSumPeriodAgAg(resultSet.getDouble(4));
                closedEntriesIncome.setSumPeriodDc(resultSet.getDouble(5));
                closedEntriesIncome.setSumPeriodCons(resultSet.getDouble(6));
                closedEntriesIncome.setSumDncAgAgFix(resultSet.getDouble(7));
                closedEntriesIncome.setSumDncConsFix(resultSet.getDouble(8));
                closedEntriesIncome.setSumDncDcFix(resultSet.getDouble(9));
            }
            System.out.println(closedEntriesIncome.toString());

            connection.close();


        } catch (NullPointerException e) {
            log.info("Result set from database is empty!");
            ClosedIncomeSums closedEntries = null;
            return closedEntries;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return closedEntriesIncome;
    }


    public int insert(ClosedEntriesIncome item) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectInsert = "INSERT INTO " + TABLE_NAME + " VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?)";

        int countInsert = -1;

        PreparedStatement statement = connection.prepareStatement(selectInsert);
        //  statement.setDouble(1, item.getId());//може не треба оскільки це просто ІД
        statement.setDouble(1, item.getTotalFixSum());
        statement.setTimestamp(2, item.getFixStart());
        statement.setTimestamp(3, item.getFixEnd());
        statement.setDouble(4, item.getDuration());
        statement.setDouble(5, item.getDurationInPeriod());
        statement.setDouble(6, item.getDurationPeriodAcc());
        statement.setDouble(7, item.getPeriodSum());
        statement.setDouble(8, item.getProjectId());
        statement.setDouble(9, item.getAgAgFix());
        statement.setDouble(10, item.getDcFix());
        statement.setDouble(11, item.getConsFix());
        statement.setDouble(12, item.getPeriodAgAg());//Закінчив тут
        statement.setDouble(13, item.getPeriodDc());
        statement.setDouble(14, item.getPeriodCons());
        statement.setDouble(15, item.getDncAgAgFix());
        statement.setDouble(16, item.getDncConsFix());
        statement.setDouble(17, item.getDncDcFix());
        statement.setDouble(18, item.getPeriodId());
        statement.setString(19, item.getPeriodName());
        statement.setTimestamp(20, item.getPeriodStart());
        statement.setTimestamp(21, item.getPeriodEnd());
        statement.setString(22, item.getPeriodState());
        statement.setString(23, item.getPeriodStartStr());
        statement.setString(24, item.getPeriodEndStr());
        statement.setTimestamp(25, new Timestamp(System.currentTimeMillis()));


        countInsert = statement.executeUpdate();


        return countInsert;

    }


    public int insert(List<ClosedEntriesIncome> item) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectInsert = "INSERT INTO " + TABLE_NAME + " VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?)";


        int countInsert = -1;

        PreparedStatement statement = connection.prepareStatement(selectInsert);
        for (ClosedEntriesIncome entriesIncome : item) {
            statement.setDouble(1, entriesIncome.getTotalFixSum());
            statement.setTimestamp(2, entriesIncome.getFixStart());
            statement.setTimestamp(3, entriesIncome.getFixEnd());
            statement.setDouble(4, entriesIncome.getDuration());
            statement.setDouble(5, entriesIncome.getDurationInPeriod());
            statement.setDouble(6, entriesIncome.getDurationPeriodAcc());
            statement.setDouble(7, entriesIncome.getPeriodSum());
            statement.setDouble(8, entriesIncome.getProjectId());
            statement.setDouble(9, entriesIncome.getAgAgFix());
            statement.setDouble(10, entriesIncome.getDcFix());
            statement.setDouble(11, entriesIncome.getConsFix());
            statement.setDouble(12, entriesIncome.getPeriodAgAg());//Закінчив тут
            statement.setDouble(13, entriesIncome.getPeriodDc());
            statement.setDouble(14, entriesIncome.getPeriodCons());
            statement.setDouble(15, entriesIncome.getDncAgAgFix());
            statement.setDouble(16, entriesIncome.getDncConsFix());
            statement.setDouble(17, entriesIncome.getDncDcFix());
            statement.setDouble(18, entriesIncome.getPeriodId());
            statement.setString(19, entriesIncome.getPeriodName());
            statement.setTimestamp(20, entriesIncome.getPeriodStart());
            statement.setTimestamp(21, entriesIncome.getPeriodEnd());
            statement.setString(22, entriesIncome.getPeriodState());
            statement.setString(23, entriesIncome.getPeriodStartStr());
            statement.setString(24, entriesIncome.getPeriodEndStr());
            statement.setTimestamp(25, new Timestamp(System.currentTimeMillis()));

            System.err.println(entriesIncome);
            statement.addBatch();

        }


        countInsert = statement.executeBatch().length;


        return countInsert;

    }


}
