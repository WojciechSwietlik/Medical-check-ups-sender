package pl.coderslab.medicalcheckupssender.Exception;

public class IdMismatchException extends Throwable {
    private String errorCode = "CODE1";

    public IdMismatchException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public IdMismatchException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
