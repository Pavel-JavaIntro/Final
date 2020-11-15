package by.pavka.library.newversion;

import by.pavka.library.BookOrder;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.SimpleListEntity;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.dao.impl.LibraryDaoFactory;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeServiceInterface;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.mapper.TableEntityMapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LibraryService implements WelcomeServiceInterface {
  private static final LibraryService INSTANCE = new LibraryService();

  private LibraryService() {}

  public static LibraryService getInstance() {
    return INSTANCE;
  }

  public <T extends SimpleListEntity> void initConstants(
      Map<Integer, String> constants, TableEntityMapper constant) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<T> dao = new LibraryDaoImpl<>(constant, connector);
      List<T> list = dao.read();
      for (T entity : list) {
        constants.put(entity.getId(), entity.getDescription());
      }
    } catch (DaoException e) {
      throw new ServiceException("Cannot initialize constants", e);
    }
  }

  public int countBooks() throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> bookDao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      return bookDao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the books", e);
    }
  }

  public int countUsers() throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<User> userDao = new LibraryDaoImpl<>(TableEntityMapper.USER, connector);
      return userDao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the users", e);
    }
  }

  @Override
  public User auth(String surname, String name, String password) throws ServiceException {
    int hashPass = password.hashCode();
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<User> userDao = new LibraryDaoImpl<>(TableEntityMapper.USER, connector);
      Criteria criteria = new Criteria();
      EntityField<String> surnameField = new EntityField<>(User.SURNAME);
      surnameField.setValue(surname);
      EntityField<String> nameField = new EntityField<>(User.NAME);
      nameField.setValue(name);
      EntityField<Integer> passField = new EntityField<>(User.PASSWORD);
      passField.setValue(hashPass);
      criteria.addConstraints(surnameField, nameField, passField);
      List<User> users = userDao.read(criteria, true);
      if (users.size() > 0) {
        return users.get(0);
      } else {
        return null;
      }
    } catch (DaoException e) {
      throw new ServiceException("Cannot authorize the user", e);
    }
  }

  @Override
  public List<Book> findBooksByEditionCode(String code) throws ServiceException {
    return null;
  }

  @Override
  public List<Book> findBooksByEdition(int id) throws ServiceException {
    return null;
  }

  @Override
  public List<Edition> findEditions(String title, String author) throws ServiceException {
    return null;
  }

  @Override
  public Book findBookByEdition(int id) throws ServiceException {
    return null;
  }

  @Override
  public void bindAuthors(EditionInfo info) throws ServiceException {

  }

  @Override
  public void bindBookAndLocation(EditionInfo info) throws ServiceException {

  }

  @Override
  public void addCode(String code) throws ServiceException {

  }

  @Override
  public int editionIdByCode(String code) throws ServiceException {
    return 0;
  }

  @Override
  public void addBook(Book book) throws ServiceException {

  }

  @Override
  public int addEdition(Edition edition) throws ServiceException {
    return 0;
  }

  @Override
  public int addAuthor(Author author) throws ServiceException {
    return 0;
  }

  @Override
  public void bindEditionAndAuthors(int editionId, int[] authorsId) throws ServiceException {

  }

  @Override
  public List<Author> findAuthors(Criteria criterion) throws ServiceException {
    return null;
  }

  @Override
  public void decommissionBook(int bookId) throws ServiceException {

  }

  @Override
  public List<User> findUsers(String surname, String name) throws ServiceException {
    return null;
  }

  @Override
  public void addUser(User user) throws ServiceException {

  }

  @Override
  public void changeStatus(int userId, int roleId) throws ServiceException {

  }

  @Override
  public void orderBook(BookOrder bookOrder) throws ServiceException {

  }

  @Override
  public void prepareOrder(BookOrder bookOrder) throws ServiceException {

  }

  @Override
  public void fulfillOrder(BookOrder dispatchedOrder) throws ServiceException {

  }

  @Override
  public Book findBookById(int bookId) throws ServiceException {
    return null;
  }

  @Override
  public void fixReturn(Book book) throws ServiceException {

  }

  @Override
  public Collection<BookOrder> getPlacedOrder() throws ServiceException {
    return null;
  }

  @Override
  public Collection<BookOrder> getPreparedOrders() {
    return null;
  }
}
