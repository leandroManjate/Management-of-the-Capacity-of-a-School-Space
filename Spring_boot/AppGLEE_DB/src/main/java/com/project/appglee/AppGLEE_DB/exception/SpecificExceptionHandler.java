package com.project.appglee.AppGLEE_DB.exception;
import com.project.appglee.AppGLEE_DB.utils.GenericResponse;
import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.project.appglee.AppGLEE_DB.utils.Global.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpecificExceptionHandler {
    @ExceptionHandler(JDBCException.class)
    public GenericResponse sqlException(JDBCException ex) {
        return new GenericResponse("sql-exception", -1, ERRONEUS_OPERATION, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse validException(MethodArgumentNotValidException ex) {
        return new GenericResponse("valid-exception", RESP_ERROR, ERRONEUS_OPERATION, ex.getMessage());
    }

    @ExceptionHandler(FileStorageException.class)
    public GenericResponse fileStorageException(FileStorageException ex) {
        return new GenericResponse("file-storage-exception", RESP_ERROR, ERRONEUS_OPERATION, ex.getMessage());
    }

    @ExceptionHandler(MyFileNotFoundException.class)
    public GenericResponse myFileNotFoundException(MyFileNotFoundException exception) {
        return new GenericResponse("my-file-not-found-exception", RESP_ERROR, INCORRECT_OPERATION, exception.getMessage());
    }
}
