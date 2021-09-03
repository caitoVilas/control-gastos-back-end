package com.caito.controlgastos.exceptions;

import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.exceptions.customs.EmptyImputException;
import com.caito.controlgastos.exceptions.customs.Forbidden;
import javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> notFoundException(Exception e, HttpServletRequest request){

        ErrorResponse error = new ErrorResponse(404, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            DuplicateKeyException.class,
            MethodArgumentTypeMismatchException.class,
            EmptyImputException.class
    })
    protected ResponseEntity<?> badRequestException(Exception e, HttpServletRequest request){

        ErrorResponse error = new ErrorResponse(400, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Forbidden.class)
    protected ResponseEntity<?> forbidden(Exception e, HttpServletRequest request){

        ErrorResponse error = new ErrorResponse(403, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?>  handleConstraintVilotion(ConstraintViolationException e,
                                                          HttpServletRequest request){
        String message = "";
        for (ConstraintViolation<?> cv : e.getConstraintViolations()){
            message = message + cv.getMessage();
        }
        ErrorResponse error = new ErrorResponse(400, LocalDateTime.now(),message,
                request.getRequestURI());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
