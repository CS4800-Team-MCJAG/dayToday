package com.mcjag.daytoday.providers;

import com.mcjag.daytoday.tables.User;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UserProvider {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");

    public String addUser(User u) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(u);
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
