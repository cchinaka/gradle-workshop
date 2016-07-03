package ng.com.workshop.exception;

public class RecordNotFoundException extends Exception {

    private static final long serialVersionUID = -7399229177928897900L;


    public RecordNotFoundException() {
        super();
    }


    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }


    public RecordNotFoundException(String message) {
        super(message);
    }


    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
