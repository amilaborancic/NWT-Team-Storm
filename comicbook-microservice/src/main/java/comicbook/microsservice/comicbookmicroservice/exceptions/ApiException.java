package comicbook.microsservice.comicbookmicroservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String meesage;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String meesage, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.meesage = meesage;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
    public String getMeesage() {
        return meesage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
