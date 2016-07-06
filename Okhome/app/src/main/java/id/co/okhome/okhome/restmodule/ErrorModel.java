package id.co.okhome.okhome.restmodule;

/**
 * Created by josongmin on 2016-02-17.
 */
public class ErrorModel {
    public String resultCode, resultMessage, resultObject;

    public ErrorModel(String resultCode, String resultMessage, String resultObject){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultObject = resultObject;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultObject() {
        return resultObject;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
