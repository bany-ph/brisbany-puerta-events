package bany.events.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Long id ) {
        super( resource + " with Id: " + id + " was not found");
    }
}
