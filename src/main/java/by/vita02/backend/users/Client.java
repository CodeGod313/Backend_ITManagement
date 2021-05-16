package by.vita02.backend.users;

import by.vita02.backend.enums.Role;

public class Client extends User {
    private String passportNumber;

    public Client(String nickName, String name, String surname, String emailAddr, Role role, String passportNumber) {
        super(nickName, name, surname, emailAddr, role);
        this.passportNumber = passportNumber;
    }
}
