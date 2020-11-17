package com.mcjag.daytoday.providers;
/*
import com.mcjag.daytoday.tables.User;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserProvider extends JpaRepository<User, String> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");

    public String addUser(User u) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {

            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            User temp = em.find(User.class, u.getEmail());
            if(temp==null){
                em.persist(u);
            }
                em.merge(u);
            
            tx.commit();
        } catch (RuntimeException ex) {
            try {
                tx.rollback();
            } catch (RuntimeException rollbackEx) {
                System.out.println("Could not roll back transaction");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return "user created";
    }

    public List<User> displayAllUsers() {
        EntityManager em = null;
        List<User> list = new ArrayList<>();
        try {
            em = emf.createEntityManager();
            list = em.createQuery("select u from User u").getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    public String deleteUser(String email) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            User u = em.find(User.class, email);
            em.remove(u);
            tx.commit();
        } catch (RuntimeException ex) {
            try {
                tx.rollback();
            } catch (RuntimeException rollbackEx) {
                System.out.println("Could not roll back transaction");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return "user deleted";
    }

    public User findUserByEmail(String email){
        EntityManager em = null;
        EntityTransaction tx = null;
        User user = new User();
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            user = em.find(User.class, email);
            if(user.getEmail()==null){
                user.setEmail("michellee.lamm@gmail.com");
            }
            em.merge(user);
            tx.commit();
        } catch (RuntimeException ex) {
            try {
                tx.rollback();
            } catch (RuntimeException rollbackEx) {
                System.out.println("Could not roll back transaction");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }     
        return user;
    }

    public String updateUser(User u) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(u);
            tx.commit();
        } catch (RuntimeException ex) {
            try {
                tx.rollback();
            } catch (RuntimeException rollbackEx) {
                System.out.println("Could not roll back transaction");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return "user updated";
    }
}
*/