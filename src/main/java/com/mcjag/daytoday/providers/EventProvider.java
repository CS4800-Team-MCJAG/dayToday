package com.mcjag.daytoday.providers;

import com.mcjag.daytoday.chart.Chart;
import com.mcjag.daytoday.tables.Event;
import com.mcjag.daytoday.tables.User;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EventProvider {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");

    public String addEvent(Event e) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(e);
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
        return "event created";
    }

    public List<Event> displayAllEvents() {
        EntityManager em = null;
        List<Event> list = new ArrayList<>();
        try {
            em = emf.createEntityManager();
            list = em.createQuery("select e from Event e").getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    public String deleteEvent(int eventID) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Event e = em.find(Event.class, eventID);
            em.remove(e);
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
        return "event deleted";
    }

    public String updateEvent(Event e) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(e);
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
        return "event updated";
    }

    public ResponseEntity<StreamingResponseBody> getGraph(String email) throws Exception {
        var dataset = new XYSeriesCollection();
        List<DateTime> dateTimes = new ArrayList<>();
        DateTime today = new DateTime();
        for (int i = 0; i < 7; i++) {
            dateTimes.add(today.plusDays(i));
        }

        List<Date> week = new ArrayList<>();
        for (int i = 0; i < dateTimes.size(); i++) {
            Date d = dateTimes.get(i).toDate();
            d.setHours(0);
            d.setMinutes(0);
            d.setSeconds(0);
            String pattern = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(d);
            d = simpleDateFormat.parse(date);
            week.add(d);
        }

        for (int i =0 ; i< week.size(); i++) {
            System.out.println(week.get(i));
        }

        EntityManager em = null;
        int[] eventCounter = {0, 0, 0, 0, 0, 0, 0};
        List<Event> eventsList = new ArrayList<>();
        try {
            em = emf.createEntityManager();
            eventsList = em.createQuery("select e from Event e where e.email = :ee").setParameter("ee", email).getResultList();
            for (int i = 0; i < eventsList.size(); i++) {
                Event e = eventsList.get(i);
                for (int k = 0; k < week.size(); k++) {
                    Date d = e.getStartDayAndTime();
                    d.setHours(0);
                    d.setMinutes(0);
                    d.setSeconds(0);
                    if (d.compareTo(week.get(k)) == 0)
                        eventCounter[k] += 1;
                }
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        var series = new XYSeries("Events this Week");
        if (eventsList.size() > 0) {
            for (int i = 0; i < week.size(); i++) {
                series.add(i, eventCounter[i]);
            }
        }

        dataset.addSeries(series);

        JFreeChart chart = Chart.createXYLineChart(
                String.format("Events for the week of: %s", new LocalDate()),
                String.format("%s to %s", week.get(0), week.get(6)),
                "Number of Events",
                dataset
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(outputStream, chart, 700, 467);
        StreamingResponseBody streamingResponseBody = out -> {
            out.write(outputStream.toByteArray());
        };

        return new ResponseEntity<>(streamingResponseBody, HttpStatus.OK);
    }

    public String email(int eventID) {
        EntityManager em = null;
        final String username = "day2dayapplication@gmail.com";
        final String password = "Day2day!";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            em = emf.createEntityManager();
            Event e = em.find(Event.class, eventID);
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(e.getEmail())
            );
            List<User> list = em.createQuery("select u from User u where u.email = :em").setParameter("em", e.getEmail()).getResultList();
            User u = list.get(0);
            message.setSubject("You Have an Alert!");
            message.setText(String.format("Dear %s, \n\n Your event, %s, is beginning soon. \n\n %s", u.getFirstName(), e.getEventName(), e.getZoomLink()));
            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return "done";
    }
}
