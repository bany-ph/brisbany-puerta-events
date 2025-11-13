package bany.events.exceptions;

public class ResoruceNotFoundException extends RuntimeException {
    public ResoruceNotFoundException(String message) {
        super(message);
    }

    public ResoruceNotFoundException(String resource, Long id ) {
        super( resource + " with Id: " + id + " was not found");
    }
}
