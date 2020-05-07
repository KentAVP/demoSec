package CRUD.dao;

import CRUD.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Transactional
    public List<User> getAll() {
        Transaction transaction = null;
        List<User> listOfUser = null;
        try {
            // start a transaction
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // get an user object

            listOfUser = session.createQuery("from User").list(); // забераем из наименование класса

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }

    @Transactional
    public void delete(User user) {
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if (user != null) {
                session.delete(user);
                System.out.println("Вы удалили пользователя!");
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void add(User user) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void update(User user) {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public User getbyID(int id) {
        Transaction transaction = null;
        User user = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
    @Transactional
    public User findByUsername(String username) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("FROM User where username = '"+username+"'").list();
        return users.get(0);
    }
}
