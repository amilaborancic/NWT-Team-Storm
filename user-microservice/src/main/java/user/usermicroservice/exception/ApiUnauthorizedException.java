package user.usermicroservice.exception;

public class ApiUnauthorizedException extends RuntimeException {
    public ApiUnauthorizedException(String message){ super(message); }
}
