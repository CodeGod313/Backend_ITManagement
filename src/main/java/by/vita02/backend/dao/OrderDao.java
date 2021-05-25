package by.vita02.backend.dao;

import by.vita02.backend.order.Order;
import by.vita02.backend.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDao {
  public List<Order> getAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Query query = session.createQuery("from Order");
    List<Order> orders= query.list();
    session.close();
    return orders;
  }
}
