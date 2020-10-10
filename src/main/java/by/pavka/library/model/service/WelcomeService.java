package by.pavka.library.model.service;

import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.dao.impl.LibraryDaoFactory;
import by.pavka.library.model.dao.impl.SimpleLibraryDao;
import by.pavka.library.model.mapper.TableEntityMapper;

import java.util.List;

public class WelcomeService {
  private static WelcomeService instance = new WelcomeService();

  private WelcomeService() {}

  public static WelcomeService getInstance() {
    return instance;
  }
  public int countBooks() throws ServiceException {
    try {
      LibraryDao<Book> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.BOOK);
      return dao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the books", e);
    }
  }

  public int countUsers() throws ServiceException {
    try {
      LibraryDao<User> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.USER);
      return dao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the users", e);
    }
  }

//  public boolean auth(String surname, String name, String password) throws ServiceException {
//    int hashPass = password.hashCode();
//    try {
//      LibraryDao<User> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.USER);
//      Criteria criteria = new Criteria();
//      EntityField<String> surnameField = new EntityField<>("surname");
//      surnameField.setValue(surname);
//      EntityField<String> nameField = new EntityField<>("name");
//      nameField.setValue(name);
//      EntityField<Integer> passField = new EntityField<>("password");
//      passField.setValue(hashPass);
//      criteria.addConstraints(surnameField, nameField, passField);
//      return dao.contains(criteria, true);
//    } catch (DaoException e) {
//      throw new ServiceException("Cannot authorize the user", e);
//    }
//  }

  public User auth(String surname, String name, String password) throws ServiceException {
    int hashPass = password.hashCode();
    try {
      LibraryDao<User> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.USER);
      Criteria criteria = new Criteria();
      EntityField<String> surnameField = new EntityField<>("surname");
      surnameField.setValue(surname);
      EntityField<String> nameField = new EntityField<>("name");
      nameField.setValue(name);
      EntityField<Integer> passField = new EntityField<>("password");
      passField.setValue(hashPass);
      criteria.addConstraints(surnameField, nameField, passField);
      List<User> users = dao.read(criteria, true);
      if (users.size() > 0) {
        return users.get(0);
      } else {
        return null;
      }
    } catch (DaoException e) {
      throw new ServiceException("Cannot authorize the user", e);
    }
  }
}
