package by.pavka.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class.getName());

  public static void main(String[] args) {
    // TODO remove this class when finish
    String s = "This is a Java string";
    int i = 2987023;
    System.out.println("s.hashCode() = " + s.hashCode());
    System.out.println("hash = " + Objects.hash(s));
    System.out.println("hashCode = " + Objects.hashCode(s));
    System.out.println(Objects.hash(i));
    System.out.println(Objects.hashCode(i));
    System.out.println("admin".hashCode());
    System.out.println("System Hash = " + System.identityHashCode(s));
    // logger.warn("Всем труба");
    Locale.setDefault(new Locale("ru"));

    // ConfirmationMailSender.sendInvitation("pavkascool@gmail.com");
    System.out.println(Locale.getDefault());
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      System.out.println(drivers.nextElement());
    }

    Object test = new Object();
    System.out.println(test.hashCode());
  }
}
