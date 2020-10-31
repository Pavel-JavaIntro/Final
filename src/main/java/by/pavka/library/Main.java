package by.pavka.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class.getName());

  public static void main(String[] args) {

    List list = new CopyOnWriteArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer a = (Integer)iterator.next();
      System.out.println(a);
      list.remove(a);
    }
    System.out.println(list.size());
    System.out.println("admin".hashCode());
  }

}
