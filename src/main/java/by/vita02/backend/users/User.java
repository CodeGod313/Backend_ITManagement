package by.vita02.backend.users;

import by.vita02.backend.enums.Role;

public abstract class User {
    private String nickName;
    private String name;
    private String surname;
    private String emailAddr;
    private Role role;

    public User(String nickName, String name, String surname, String emailAddr, Role role) {
        this.nickName = nickName;
        this.name = name;
        this.surname = surname;
        this.emailAddr = emailAddr;
        this.role = role;
    }
}
