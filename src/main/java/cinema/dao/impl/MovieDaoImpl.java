package cinema.dao.impl;

import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.model.Movie;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        logger.warn("A try to add movie " + movie);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't insert movie " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> movies = session.createQuery("from Movie", Movie.class);
            return movies.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Couldn't get movies", e);
        }
    }
}
