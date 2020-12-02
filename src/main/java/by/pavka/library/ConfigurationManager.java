package by.pavka.library;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigurationManager {
  private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

  private ConfigurationManager() {}

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}
