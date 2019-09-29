package ua.company.spring.SpringUniversityAdmissionSystem.service.exception;

public class SignInException extends ServiceException {
    public SignInException() {
    }

    public SignInException(String message) {
        super(message);
    }

    public SignInException(Throwable cause) {
        super(cause);
    }

    public SignInException(String message, Throwable cause) {
        super(message, cause);
    }
}
