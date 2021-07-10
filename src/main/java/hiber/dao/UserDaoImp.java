package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override

   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User",User.class).getResultList();

   }
   @Override
   public User getUserByCar(String model,int series) {

      User user = sessionFactory.getCurrentSession().
              createQuery("select us FROM User us where us.car.id=" +
                      " some (select id from Car where model=:model and series=:series)", User.class).
              setParameter("model", model).setParameter("series", series).setMaxResults(1).uniqueResult();

      return user;
   }

}
