package by.vita02.backend.users;

import by.vita02.backend.company.Company;
import by.vita02.backend.enums.Role;
import by.vita02.backend.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String passportNumber;
    private Company company;
    private List<Order> orders = new ArrayList<>();
    public Client(String nickName, String name, String surname,
                  String emailAddr, Role role, String passportNumber,
                  Company company) {
        super(nickName, name, surname, emailAddr, role);
        this.passportNumber = passportNumber;
        this.company = company;
    }
}
