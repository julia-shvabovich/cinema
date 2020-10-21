package cinema.dao.impl;

import cinema.dao.OrderDao;
import cinema.exception.DataProcessingException;
import cinema.model.Order;
import cinema.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        logger.warn("A try to add order " + order);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't insert order " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orders = session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets "
                    + "WHERE o.user = :user", Order.class);
            orders.setParameter("user", user);
            return orders.getResultList();
        }
    }
}
