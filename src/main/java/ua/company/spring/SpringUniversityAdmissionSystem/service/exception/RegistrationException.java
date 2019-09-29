package ua.company.spring.SpringUniversityAdmissionSystem.service.exception;

public class RegistrationException extends ServiceException {
    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable cause) {
        super(cause);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
