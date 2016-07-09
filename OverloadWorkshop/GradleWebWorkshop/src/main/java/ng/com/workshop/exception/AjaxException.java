package ng.com.workshop.exception;

public class AjaxException extends Exception {

    private static final long serialVersionUID = -7399229177928897900L;


    public AjaxException() {
        super();
    }


    public AjaxException(Throwable cause) {
        super(cause);
    }


    public AjaxException(String message) {
        super(message);
    }


    public AjaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
