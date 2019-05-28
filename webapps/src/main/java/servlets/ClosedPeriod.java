package servlets;

import co.maxbi.config.Configurations;
import co.maxbi.logic.workflow.fasade.ClosePeriodFasade;
import co.maxbi.text.Decoder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/close_period")
public class ClosedPeriod extends HttpServlet {
    final static Logger logger = Logger.getLogger(ClosedPeriod.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configurations.configFromFile();//инициализация имен таблиц из файла конфигурации

        Decoder decoderText = new Decoder();
        String inputStreamJson = decoderText.read(req.getInputStream(), "UTF-8");
        logger.info("inputStreamJson = " + inputStreamJson);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(inputStreamJson);

        String periodId = jo.get("periodId").getAsString();
        String periodName = jo.get("periodName").getAsString();
        String periodStartDate = jo.get("periodStartDate").getAsString();
        String periodEndDate = jo.get("periodEndDate").getAsString();

        logger.info("periodId = " + periodId + "; periodName = " + periodName);
        logger.info("periodStartDate = " + periodStartDate + "; periodEndDate = " + periodEndDate);

        Thread thread = new Thread(new ClosePeriodFasade(periodId, periodName, periodStartDate, periodEndDate));
        thread.start();

        resp.setStatus(200);
    }


}
