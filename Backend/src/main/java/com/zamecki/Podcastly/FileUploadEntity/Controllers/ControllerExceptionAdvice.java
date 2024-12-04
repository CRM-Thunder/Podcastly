package com.zamecki.Podcastly.FileUploadEntity.Controllers;

import com.zamecki.Podcastly.CustomContainers.CustomDate;
import com.zamecki.Podcastly.FileUploadEntity.ErrorResponses.MultipleErrorResponse;
import com.zamecki.Podcastly.FileUploadEntity.ErrorResponses.SingleErrorResponse;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.AllUpdateFieldsAreNullException;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.FileTypeViolationException;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.ObjectNotFoundException;
import com.zamecki.Podcastly.FileUploadEntity.Exceptions.PostNotSavedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<SingleErrorResponse> handleObjectNotFoundException(ObjectNotFoundException exception) {
        CustomDate exceptionDate = new CustomDate();
        return new ResponseEntity<>(SingleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).message(exception.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PostNotSavedException.class)
    public ResponseEntity<SingleErrorResponse> handlePostNotSavedException(PostNotSavedException exception){
        CustomDate exceptionDate = new CustomDate();
        return new ResponseEntity<>(SingleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).message(exception.getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(FileTypeViolationException.class)
    public ResponseEntity<SingleErrorResponse> handleFileTypeViolationException(FileTypeViolationException exception){
        CustomDate exceptionDate = new CustomDate();
        return new ResponseEntity<>(SingleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).message(exception.getMessage()).build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    @ExceptionHandler(AllUpdateFieldsAreNullException.class)
    public ResponseEntity<SingleErrorResponse> handleAllUpdateFieldsAreNullException(AllUpdateFieldsAreNullException exception){
        CustomDate exceptionDate = new CustomDate();
        return new ResponseEntity<>(SingleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MultipleErrorResponse> handleConstraintViolationException(ConstraintViolationException exception){
        CustomDate exceptionDate= new CustomDate();
        List<String> messages=exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return new ResponseEntity<>(MultipleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).messages(messages).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultipleErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        CustomDate exceptionDate= new CustomDate();
        List<String> messages=exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(MultipleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).messages(messages).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<SingleErrorResponse> handleMissingServletRequestParameterException (MissingServletRequestParameterException exception){
        CustomDate exceptionDate=new CustomDate();
        String message="Parameter: "+exception.getParameterName()+" must be included!";
        return new ResponseEntity<>(SingleErrorResponse.builder().timestamp(exceptionDate.getDateTime()).message(message).build(),HttpStatus.BAD_REQUEST);
    }


}


