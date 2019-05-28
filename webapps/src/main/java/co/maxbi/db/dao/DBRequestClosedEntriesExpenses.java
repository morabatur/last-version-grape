package co.maxbi.db.dao;

import co.maxbi.config.Configurations;
import co.maxbi.db.connector.DatabaseConnector;
import co.maxbi.db.pojo.local.ClosedEntriesExpenses;
import co.maxbi.db.pojo.local.ClosedExpensesPeriods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з таблицею закритих періодів
 */
public class DBRequestClosedEntriesExpenses {
    //fixme Визначитись з системою відлову помилок в разі відсутності таблиці
    private static String TABLE_NAME = Configurations.getExpensesTableName();// = "dbo.ClosedEntriesExpenses";
        //"TestSync_v3.ClosedEntriesExpenses";


    public static String getTableName() {
        return TABLE_NAME;
    }

    public static void setTableName(String tableName) {
        TABLE_NAME = tableName;
    }

    public double selectSumPeriodExpenseAcc(int expenseId, String periodStart) throws SQLException {

        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectSql = "SELECT SUM(PeriodSum ) AS SumPeriodExpenseAcc\n" +
                "FROM " + TABLE_NAME + "\n" +
                "WHERE ExpenseId = ? AND PeriodEnd < ?";

        double sum = -1;


        PreparedStatement statement = connection.prepareStatement(selectSql);

        statement.setInt(1, expenseId);
        statement.setString(2, periodStart);


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            sum = resultSet.getDouble(1);

        }

        connection.close();


        return sum;
    }

    public ClosedExpensesPeriods selectClosedProjectData(int idExpenses, String periodStart) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectClosedProject = "SELECT SUM(PeriodSum) AS PeriodExpenseAcc, SUM(DurationInPeriod) AS DurationPeriodAcc, MIN(PeriodStart) AS StartMin\n" +
                "FROM " + TABLE_NAME + "\n" +
                "WHERE ExpenseId = ? AND PeriodEnd < ?";

        ClosedExpensesPeriods closedEntriesExpensesPOJO = null;

        PreparedStatement statement = connection.prepareStatement(selectClosedProject);

        statement.setInt(1, idExpenses);
        statement.setString(2, periodStart);


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            closedEntriesExpensesPOJO = new ClosedExpensesPeriods(resultSet.getDouble(1),
                    resultSet.getInt(2), resultSet.getDate(3));
        }

        connection.close();


        return closedEntriesExpensesPOJO;

    }

    public List<ClosedEntriesExpenses> selectExpensesId(String periodStart) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectClosedProject = "SELECT ExpenseId, TotalFixSum, FixStart, FixEnd, Duration, DurationInPeriod, " +
                "DurationPeriodAcc, PeriodSum, FixedExpProjId, PeriodEndStr, FixedExpCategory#GroupId," +
                " FixedExpCategory#GroupName, Id FROM " + TABLE_NAME + "\n" +
                "WHERE CorrClosed = 0 AND PeriodEnd < ?";

        List<ClosedEntriesExpenses> expensesIdList = new ArrayList<>();


        PreparedStatement statement = connection.prepareStatement(selectClosedProject);

        statement.setString(1, periodStart);


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ClosedEntriesExpenses closedEntriesExpenses = new ClosedEntriesExpenses();
            closedEntriesExpenses.setExpenseId(resultSet.getInt(1));
            closedEntriesExpenses.setTotalFixSum(resultSet.getDouble(2));
            closedEntriesExpenses.setFixStart(resultSet.getDate(3));
            closedEntriesExpenses.setFixEnd(resultSet.getDate(4));
            closedEntriesExpenses.setDuration(resultSet.getInt(5));
            closedEntriesExpenses.setDurationInPeriod(resultSet.getInt(6));
            closedEntriesExpenses.setDurationPeriodAcc(resultSet.getDouble(7));
            closedEntriesExpenses.setPeriodSum(resultSet.getDouble(8));
            closedEntriesExpenses.setFixedExpProjId(resultSet.getDouble(9));
            closedEntriesExpenses.setPeriodEndStr(resultSet.getString(10));
            closedEntriesExpenses.setFixedExpCategory_GroupId(resultSet.getDouble(11));
            closedEntriesExpenses.setFixedExpCategory_GroupName(resultSet.getString(12));
            closedEntriesExpenses.setId(resultSet.getInt(13));

            expensesIdList.add(closedEntriesExpenses);
        }

        connection.close();


        return expensesIdList;
    }

    public int updateCorrClosedStatus(int id) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectClosedProject = "UPDATE " + TABLE_NAME + " " +
                "SET CorrClosed = 1, ModificationDate = ? WHERE Id = ?";

        int countUpdate = -1;
        PreparedStatement statement = connection.prepareStatement(selectClosedProject);
        statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        statement.setInt(2, id);
        countUpdate = statement.executeUpdate();


        return countUpdate;

    }

    public int insert(co.maxbi.db.pojo.global.ClosedEntriesExpenses item) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();


        String selectInsert = "INSERT INTO " + TABLE_NAME + " VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int countInsert = -1;


        PreparedStatement statement = connection.prepareStatement(selectInsert);
        statement.setDouble(1, item.getExpenseId());
        statement.setDouble(2, item.getTotalFixSum());
        statement.setTimestamp(3, item.getFixStart());
        statement.setTimestamp(4, item.getFixEnd());
        statement.setDouble(5, item.getDuration());
        statement.setDouble(6, item.getDurationInPeriod());
        statement.setDouble(7, item.getDurationPeriodAcc());
        statement.setDouble(8, item.getPeriodSum());
        statement.setDouble(9, item.getFixedExpProjId());
        statement.setString(10, item.getCorrClosed());
        statement.setString(11, item.getShifted());
        statement.setDouble(12, item.getPeriodId());
        statement.setString(13, item.getPeriodName());
        statement.setTimestamp(14, item.getPeriodStart());
        statement.setTimestamp(15, item.getPeriodEnd());
        statement.setString(16, item.getPeriodState());
        statement.setString(17, item.getPeriodStartStr());
        statement.setString(18, item.getPeriodEndStr());
        //Дані вирази можуть набвати NULL значення тому передаються в базу як об'єкти
        statement.setObject(19, item.getFixedExpCategory_GroupId());
        statement.setObject(20, item.getFixedExpCategory_GroupName());

        statement.setTimestamp(21, new Timestamp(System.currentTimeMillis()));


        countInsert = statement.executeUpdate();


        connection.close();

        return countInsert;
    }

    public int insert(List<co.maxbi.db.pojo.global.ClosedEntriesExpenses> item) throws SQLException {
        DatabaseConnector connectorDAO = new DatabaseConnector();
        Connection connection = connectorDAO.conect();

        String selectInsert = "INSERT INTO " + TABLE_NAME + " VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int countInsert = -1;

        PreparedStatement statement = connection.prepareStatement(selectInsert);

        for (co.maxbi.db.pojo.global.ClosedEntriesExpenses closedEntriesExpenses : item) {
            statement.setDouble(1, closedEntriesExpenses.getExpenseId());
            statement.setDouble(2, closedEntriesExpenses.getTotalFixSum());
            statement.setTimestamp(3, closedEntriesExpenses.getFixStart());
            statement.setTimestamp(4, closedEntriesExpenses.getFixEnd());
            statement.setDouble(5, closedEntriesExpenses.getDuration());
            statement.setDouble(6, closedEntriesExpenses.getDurationInPeriod());
            statement.setDouble(7, closedEntriesExpenses.getDurationPeriodAcc());
            statement.setDouble(8, closedEntriesExpenses.getPeriodSum());
            statement.setDouble(9, closedEntriesExpenses.getFixedExpProjId());
            statement.setString(10, closedEntriesExpenses.getCorrClosed());
            statement.setString(11, closedEntriesExpenses.getShifted());
            statement.setDouble(12, closedEntriesExpenses.getPeriodId());
            statement.setString(13, closedEntriesExpenses.getPeriodName());
            statement.setTimestamp(14, closedEntriesExpenses.getPeriodStart());
            statement.setTimestamp(15, closedEntriesExpenses.getPeriodEnd());
            statement.setString(16, closedEntriesExpenses.getPeriodState());
            statement.setString(17, closedEntriesExpenses.getPeriodStartStr());
            statement.setString(18, closedEntriesExpenses.getPeriodEndStr());
            statement.setDouble(19, closedEntriesExpenses.getFixedExpCategory_GroupId());
            statement.setString(20, closedEntriesExpenses.getFixedExpCategory_GroupName());
            statement.setTimestamp(21, new Timestamp(System.currentTimeMillis()));


            statement.addBatch();
        }


        countInsert = statement.executeBatch().length;

        connection.close();

        System.out.println("Вставлено строк " + countInsert);

        return countInsert;
    }
}
