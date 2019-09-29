package ua.company.spring.SpringUniversityAdmissionSystem.util;

public enum UserStatuses {
    UNKNOWN(1),
    SUCCESS(2),
    FAIL(3);

    private final int id;

    UserStatuses(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
