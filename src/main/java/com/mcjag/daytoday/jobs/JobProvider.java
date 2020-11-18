package com.mcjag.daytoday.jobs;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.tables.Event;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String eventAlert = dateFormat.format(e.getAlert());
                String now = dateFormat.format(date);
                if (eventAlert.compareTo(now) == 0) {
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
