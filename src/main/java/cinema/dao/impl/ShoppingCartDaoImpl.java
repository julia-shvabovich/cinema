package cinema.dao.impl;

import cinema.dao.ShoppingCartDao;
import cinema.exception.DataProcessingException;
import cinema.model.ShoppingCart;
import cinema.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartDaoImpl.class);
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        logger.warn("A try to add shopping cart " + shoppingCart);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't insert cart " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ShoppingCart cart "
                    + "LEFT JOIN FETCH cart.tickets "
                    + "JOIN FETCH cart.user "
                    + "WHERE cart.user = :user", ShoppingCart.class)
                    .setParameter("user", user)
                    .getSingleResult();
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        logger.warn("A try to update shopping cart " + shoppingCart);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't update cart " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
