package by.vita02.backend.order;

import java.util.Date;

public class Order {
    private boolean isAccepted;
    private Date date;
    private int cost;
    private int  count;

    public Order(boolean isAccepted, int cost, int count) {
        this.isAccepted = isAccepted;
        this.cost = cost;
        this.count = count;
        date = new Date();
    }
}
