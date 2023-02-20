package com.music.gu.common;

import lombok.Data;

import static java.net.HttpURLConnection.*;

@Data
public class ReturnMessage {

    private int code;
    private String message;
    private String type;
    private Boolean success;
    private Object data;

    /**
     * 成功，不返回数据
     * @param message
     * @return
     */
    public static ReturnMessage success(String message){
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(HTTP_OK);
        returnMessage.setMessage(message);
        returnMessage.setSuccess(true);
        returnMessage.setData(null);
        return returnMessage;
    }

    /**
     * 成功且返回数据
     * @param message
     * @param data
     * @return
     */
    public static ReturnMessage success(String message, Object data){
        ReturnMessage returnMessage = success(message);
        returnMessage.setData(data);
        return returnMessage;
    }

    /**
     * 成功但返回警告
     * @param message
     * @return
     */
    public static ReturnMessage warning(String message) {
        ReturnMessage returnMessage = success(message);
        returnMessage.setType("warning");
        return returnMessage;
    }

    /**
     * 错误，返回信息
     * @param message
     * @return
     */
    public static ReturnMessage error(String message) {
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(HTTP_BAD_REQUEST);
        returnMessage.setMessage(message);
        returnMessage.setData(null);
        returnMessage.setSuccess(false);
        returnMessage.setType("error");
        return returnMessage;
    }

    /**
     * 严重错误，返回Code:500
     * @param message
     * @return
     */
    public static ReturnMessage fatal(String message) {
        ReturnMessage returnMessage = error(message);
        returnMessage.setCode(HTTP_SERVER_ERROR);
        return returnMessage;
    }

}
