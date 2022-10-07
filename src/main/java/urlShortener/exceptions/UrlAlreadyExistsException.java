package urlShortener.exceptions;

public class UrlAlreadyExistsException extends Exception{

    public UrlAlreadyExistsException(String message) {
        super(message);
    }
}
