package cinema.dao.impl;

import cinema.dao.TicketDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.Ticket;
import cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger logger = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        logger.warn("A try to add ticket " + ticket);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't insert ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
