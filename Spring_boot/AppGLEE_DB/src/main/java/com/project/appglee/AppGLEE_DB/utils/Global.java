package com.project.appglee.AppGLEE_DB.utils;

public class Global {
    public enum Status {
        A,
        I
    }

    public static final String TYPE_RESULT = "result";
    public static final String TYPE_DATA = "data";
    public static final String TYPE_AUTH = "auth";
    public static final int RESP_OK = 1;
    public static final int RESP_WARNING = 0;
    public static final int RESP_ERROR = -1;
    public static final String CORRECT_OPERATION = "Operation completed correctly";
    public static final String INCORRECT_OPERATION = "The operation could not be completed";
    public static final String ERRONEUS_OPERATION = "There was an error when performing the operation";
    public static final String AUTH_SECRET = "webservice2";
}
