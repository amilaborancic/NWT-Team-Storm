package catalogue.microsservice.cataloguemicroservice.exception;

public class ApiUnauthorizedException extends RuntimeException {
    public ApiUnauthorizedException(String message){ super(message); }
}
