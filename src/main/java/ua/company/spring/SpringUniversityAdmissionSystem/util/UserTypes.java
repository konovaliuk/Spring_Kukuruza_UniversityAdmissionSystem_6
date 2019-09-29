package ua.company.spring.SpringUniversityAdmissionSystem.util;

public enum UserTypes {
    STUDENT(1),
    ADMIN(2);

    private final int id;

    UserTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
