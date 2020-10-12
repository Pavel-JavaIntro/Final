package by.pavka.library.model.mapper;

import by.pavka.library.model.LibraryFatalException;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ConstantManager {

  public static final String ADMIN = "admin";
  public static final String LIBRARIAN = "librarian";
  public static final String SUBSCRIBER = "subscriber";
  public static final String READER = "reader";
  public static final String VISITOR = "visitor";

  private static final Logger logger = LogManager.getLogger(ConstantManager.class.getName());
  private static final Map<Integer, String> locations = new HashMap<>();
  private static final Map<Integer, String> operations = new HashMap<>();
  private static final Map<Integer, String> roles = new HashMap<>();

  private ConstantManager()  {}

  static {
    WelcomeService service = WelcomeService.getInstance();
    try {
      service.initConstants(locations, TableEntityMapper.LOCATION);
      service.initConstants(operations, TableEntityMapper.OPERATION);
      service.initConstants(roles, TableEntityMapper.ROLE);
    } catch (ServiceException e) {
      logger.error("Cannot initialize constants");
      throw new LibraryFatalException("Cannot initialize constants");
    }
  }

  public static String getLocationById(int i) {
    return locations.get(i);
  }

  public static String getOperationById(int i) {
    return operations.get(i);
  }

  public static String getRoleById(int i) {
    return roles.get(i);
  }
}
