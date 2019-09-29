package ua.company.spring.SpringUniversityAdmissionSystem.service.exception;

public class SubmitSpecialtyException extends ServiceException {
    public SubmitSpecialtyException() {
    }

    public SubmitSpecialtyException(String message) {
        super(message);
    }

    public SubmitSpecialtyException(Throwable cause) {
        super(cause);
    }

    public SubmitSpecialtyException(String message, Throwable cause) {
        super(message, cause);
    }
}
