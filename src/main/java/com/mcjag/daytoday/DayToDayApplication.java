package com.mcjag.daytoday;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mcjag.daytoday.chart.Chart;
import com.mcjag.daytoday.tables.Event;
import com.mcjag.daytoday.tables.User;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.joda.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// for_A4
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;

@SpringBootApplication
@RestController
public class DayToDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayToDayApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/calendar")
	public String calendar() {
		return "Welcome to your Calendar!";
	}

	@GetMapping("/onThisDay")
	public String onThisDay() throws IOException {
		Document doc = Jsoup.connect("https://www.history.com/this-day-in-history").get();
		String keywords = doc.select("meta[itemprop=name]").get(1).attr("content");
		return String.format("On this day: %s", keywords);
	}
	
	@GetMapping("/tomorrow")
	public String tomorrow() {
		LocalDate today = new LocalDate();
		LocalDate tomorrow = today.plusDays(1);
		return("Tomorrow is " + tomorrow.toString("MM/dd/yyyy"));
	}
	
	@GetMapping("/bye")
	public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Bye %s!", name);
	}
	
	@GetMapping("/for_A4")
	public String for_A4() throws MalformedURLException, IOException
	{
		 InputStream in = new URL( "https://commons.apache.org" ).openStream();
		 try {
		   System.out.println( IOUtils.toString( in ) );
		 } finally {
		   IOUtils.closeQuietly(in);
		 }
		return "";
	}

	@PostMapping("/user")
	public String addUser(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@GetMapping("/users")
	public List<User> displayAllUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@DeleteMapping("/user/{email}")
	public String deleteUser(@PathVariable("email") String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@PutMapping("/user")
	public String updateUser(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@PostMapping("/event")
	public String addEvent(Event e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@GetMapping("/events")
	public List<Event> displayAllEvents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@DeleteMapping("/event/{eventID}")
	public String deleteEvent(@PathVariable("eventID") int eventID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Event u = em.find(Event.class, eventID);
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
		return "event deleted";
	}

	@PutMapping("/event")
	public String updateEvent(Event e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dayToday");
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

	@GetMapping("/email")
	public String email() {
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
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("jongbaejeon@cpp.edu")
            );
            message.setSubject("You Have an Alert!");
            message.setText("Dear Recepient,"
                    + "\n\n You have a zoom meeting: ");
            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		return "done";
	}

	@GetMapping(value = "/graph", produces = "image/png")
	public ResponseEntity<StreamingResponseBody> getGraph() throws Exception {
		var dataset = new XYSeriesCollection();
		var series = new XYSeries("Events this Week");
		series.add(1, 3);
		series.add(2, 1);
		series.add(3, 7);
		series.add(4, 11);
		series.add(5, 4);
		series.add(6, 3);
		series.add(7, 0);

		dataset.addSeries(series);

		JFreeChart chart = Chart.createXYLineChart(
				String.format("Events for the week of: %s", new LocalDate()),
				"Sunday-Saturday",
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
}
