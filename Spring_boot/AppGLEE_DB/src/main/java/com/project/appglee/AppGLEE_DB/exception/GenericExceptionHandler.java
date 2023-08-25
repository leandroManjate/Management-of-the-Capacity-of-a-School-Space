package com.project.appglee.AppGLEE_DB.exception;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import com.project.appglee.AppGLEE_DB.utils.Global;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(Exception.class)
    public GenericResponse genericException(Exception ex) {
        return new GenericResponse("exception", -1, Global.ERRONEUS_OPERATION, ex.getMessage());
    }
}
