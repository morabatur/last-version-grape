package co.maxbi.rest;

import co.maxbi.autorization.TokenMaster;
import org.apache.log4j.Logger;

import java.io.IOException;

public class SharePointUpdater {
    final static Logger logger = Logger.getLogger(SharePointUpdater.class);

    private String successStatus;
    private String failStatus;
    private String periodId;

    public SharePointUpdater(String successStatus, String failStatus, String periodId) {
        this.successStatus = successStatus;
        this.failStatus = failStatus;
        this.periodId = periodId;
    }

    public void sendStatusOK(String field) {
        sendSharePointStatus(successStatus, field);
    }


    public void sendStatusFAIL(String field) {
        sendSharePointStatus(failStatus, field);

    }

    private void sendSharePointStatus(String status, String field) {
        TokenMaster tokenMaster = TokenMaster.create();
        DataLoader dataLoader = new DataLoader(tokenMaster);
        try {
            dataLoader.updateldInSP(periodId, field, status);
        } catch (IOException e) {
            logger.error("Cant set status in SharePoint", e);
        }


    }
}
