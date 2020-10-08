package by.pavka.library;

import java.util.Objects;

public class Main {
  public static void main(String args[]) {
   //TODO remove this class when finish
    String s = "abc1";
    int i = 2987023;
    System.out.println("s.hashCode() = " + s.hashCode());
    System.out.println("hash = " + Objects.hash(s));
    System.out.println("hashCode = " + Objects.hashCode(s));
    System.out.println(Objects.hash(i));
    System.out.println(Objects.hashCode(i));
    System.out.println("admin".hashCode());
  }
}
