package DAO;

import Entity.UsersEntity;
import Util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class UserDAO {

    Session session;

    public void add(UsersEntity user) {

        session = HibernateUtil.getSession();

        session.beginTransaction();

        session.save("User", user);
        session.getTransaction().commit();
        session.close();
    }

    public int update(UsersEntity user) {
        Session session = HibernateUtil.getSession();
        session.update(user);
        Serializable id = session.getIdentifier(user);
        session.flush();
        session.close();
        return (Integer) id;
    }

    public UsersEntity get(int id) {
        Session session = HibernateUtil.getSession();
        UsersEntity identifier = (UsersEntity) session.get(UsersEntity.class, id);
        session.close();
        return identifier;
    }

    public List<UsersEntity> list() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UsersEntity.class);
        List<UsersEntity> list = criteria.list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    public boolean registerUser(UsersEntity user) {
        Session session = HibernateUtil.getSession();
        Serializable id = session.save(user);
        return true;
    }

//    public boolean isUserExists(String login) {
//        Session session = HiberhateUtil.getSesssionFactory().openSession();
//       // Long count = (Long) session.getNamedQuery(User.SELECT_USER_COUNT_BY_LOGIN)
//                .setParameter("login", login)
//                .uniqueResult();
//        return count > 0;
//    }
}
