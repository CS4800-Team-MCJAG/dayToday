package com.mcjag.daytoday.jobs;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.tables.Event;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class JobProvider {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");

    @Scheduled(cron = "0 * * ? * *")
    public void checkData() {
        System.out.println("Checking data");
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            List<Event> list = em.createQuery("select e from Event e").getResultList();
            for (Event e: list) {
                DateTime date = new DateTime();
                DateTime eventAlert = new DateTime(e.getAlert());
                if (eventAlert.compareTo(date) == 0) {
                    EventProvider eventProvider = new EventProvider();
                    eventProvider.email(e.getEventID());
                }
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
