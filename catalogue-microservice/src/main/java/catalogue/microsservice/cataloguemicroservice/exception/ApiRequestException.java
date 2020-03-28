package catalogue.microsservice.cataloguemicroservice.exception;

//exception koji se moze bacati iz kontrolera
public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
