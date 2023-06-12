package com.electronicstore.exception;

import com.electronicstore.Config.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @author Akshay
     * @exception this GlobalException is  used to  resourceNotFoundException
     * @param ex
     * @return ApiResponse
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
     log.info("Start the resourceNotFoundException in GlobalExceptionHandler: {} ",ex);
    String message = ex.getMessage();

    ApiResponse apiResponse = new ApiResponse(message,true);
        log.info("Completed the resourceNotFoundException in GlobalExceptionHandler: {} ",ex);
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
}

    /**
     * @author Akshay
     * @exception this GlobalException is  used to  handleMethodArgumentNotValidException
     * @param ex
     * @return Map<String,Object>
     */

@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.info("Start the handleMethodArgumentNotValidException in GlobalExceptionHandler: {} ",ex);
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

    Map<String,Object> response = new HashMap<>();
    allErrors.stream().forEach(objectError ->{
        String message = objectError.getDefaultMessage();
        String field = ((FieldError) objectError).getField();
        response.put(message,field);
    });
    log.info("Completed the handleMethodArgumentNotValidException in GlobalExceptionHandler: {} ",ex);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

}

    /**
     * @author Akshay
     * @exception this GlobalException is  used to  handleBadApiRequest
     * @param ex
     * @return ApiResponse
     */


    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequestException ex) {
        log.info("Start the BadApiRequest in GlobalExceptionHandler: {} ",ex);
        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);
        log.info("Completed the BadApiRequest in GlobalExceptionHandler: {} ",ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }






}
