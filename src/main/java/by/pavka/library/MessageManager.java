package by.pavka.library;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
  private static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

  private MessageManager() {}

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }

  public static String getProperty(String key, Locale locale) {
    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    return bundle.getString(key);
  }
}
