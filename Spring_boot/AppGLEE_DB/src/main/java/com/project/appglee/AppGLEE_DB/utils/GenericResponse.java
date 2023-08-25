package com.project.appglee.AppGLEE_DB.utils;

public class GenericResponse<T> {
    private String type;
    private int resp;
    private String message;
    private T body;
    public GenericResponse() {
        type = "";
        resp = 0;
        message = "";
        body = null;
    }

    public GenericResponse(String bodyType, Object body) {
        type = "";
        resp = 0;
        message = "";
        this.body = null;
    }

    public GenericResponse(String type, int resp, String message, T body) {
        this.type = type;
        this.resp = resp;
        this.message = message;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
