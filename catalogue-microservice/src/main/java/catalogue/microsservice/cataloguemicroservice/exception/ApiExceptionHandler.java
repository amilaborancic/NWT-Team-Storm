package catalogue.microsservice.cataloguemicroservice.exception;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

//barata sa exceptions i kako se prikazuju clientu
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //create payload
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        //return response entity
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    @ExceptionHandler(value = {ApiUnauthorizedException.class})
    public ResponseEntity<Object> handleApiUnathorizedException(ApiUnauthorizedException e){
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
