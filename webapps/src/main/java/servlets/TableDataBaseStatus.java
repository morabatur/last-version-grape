package servlets;

import co.maxbi.db.dao.DBRequestClosedEntriesExpenses;
import co.maxbi.db.dao.DBRequestClosedEntriesIncome;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/table-status")
public class TableDataBaseStatus extends HttpServlet {
    private final static Logger logger = Logger.getLogger(TableDataBaseStatus.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String expensesTableName = req.getParameter("expensesTableName");
        String incomeTableName = req.getParameter("incomeTableName");

        logger.info("Servlet get expense table name: " + expensesTableName);
        logger.info("Servlet get income table name: " + incomeTableName);

        DBRequestClosedEntriesExpenses.setTableName(expensesTableName);
        logger.info("Successful change Expenses table name");

        DBRequestClosedEntriesIncome.setTableName(incomeTableName);
        logger.info("Successful change Income table name");

        resp.sendRedirect("table-db.jsp"); // wherever you wanna redirect this page.


    }
}
