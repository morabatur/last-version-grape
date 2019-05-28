<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 20.05.2019
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="co.maxbi.db.dao.DBRequestClosedEntriesExpenses" %>
<%@ page import="co.maxbi.db.dao.DBRequestClosedEntriesIncome" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="co.maxbi.config.Configurations" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Logger logger = Logger.getLogger("JSP PAGE");
    Configurations.configFromFile();
%>
<p>Current Expenses table: <b><%
    String exp = Configurations.getExpensesTableName();
    out.print(exp);
    logger.info("tab name: " + exp); %></b></p>
<p>Current Income table: <b><%
    String inc = Configurations.getIncomeTableName();
    out.print(inc);
    logger.info("tab name: " + inc); %></b></p>

<form action="table-status" method="GET">
    <p>Expenses table: <input type="text" name="expensesTableName"></p>
    <p>Income table: <input type="text" name="incomeTableName"></p>
    <input type="submit" value="Send"/>
</form>
</body>
</html>
