package cn.coding.inventorymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

    //Add Exception handler for user
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(ItemNotFoundException e){
        CustomErrorResponse error = new CustomErrorResponse(
        HttpStatus.NOT_FOUND.value(),e.getMessage(),
        System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //To Catch any exception
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception exc){
        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
