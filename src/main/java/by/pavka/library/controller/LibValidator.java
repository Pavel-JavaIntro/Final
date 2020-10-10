package by.pavka.library.controller;

public class LibValidator {
  private LibValidator() {}

  public static boolean validateLogin(String surname, String name, String pass) {
    return (surname != null
        && name != null
        && pass != null
        && !surname.isEmpty()
        && !name.isEmpty()
        && !pass.isEmpty());
  }
}
