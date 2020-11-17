package by.pavka.library.newversion;

import by.pavka.library.BookOrder;
import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.SimpleListEntity;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.dao.ManyToManyDao;
import by.pavka.library.model.dao.impl.LibraryDaoFactory;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.service.ServiceException;
import by.pavka.library.model.service.WelcomeServiceInterface;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.mapper.TableEntityMapper;

import java.sql.SQLException;
import java.util.*;

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
    List<Book> result = new ArrayList<>();
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> dao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      Criteria criteria = new Criteria();
      EntityField<Integer> edId = new EntityField<>(Book.EDITION_ID);
      edId.setValue(id);
      criteria.addConstraint(edId);
      result.addAll(dao.read(criteria, true));
    } catch (DaoException e) {
      throw new ServiceException("Cannot find books", e);
    }
    return result;
  }

  @Override
  public List<Edition> findEditions(String title, String author) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      ManyToManyDao<Edition, Author> dao = new ManyToManyDaoImpl(connector);
      if (title.isEmpty() && author.isEmpty()) {
        return dao.read();
      }
      List<Edition> titleEditions = null;
      List<Edition> authorEditions = null;

      if (!title.isEmpty()) {
        Criteria criteriaT = new Criteria();
        EntityField<String> titleField = new EntityField<>(Edition.TITLE);
        titleField.setValue(title);
        criteriaT.addConstraint(titleField);
        titleEditions = dao.read(criteriaT, false);
        if (titleEditions.isEmpty()) {
          return new ArrayList<>();
        }
      }

      List<Author> authorList = null;
      if (!author.isEmpty()) {
        try (LibraryDao<Author> authorDao =
            LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.AUTHOR)) {
          Criteria criteriaA = new Criteria();
          EntityField<String> authorField = new EntityField<>(Author.SURNAME);
          authorField.setValue(author);
          criteriaA.addConstraint(authorField);
          authorList = authorDao.read(criteriaA, false);
          if (authorList.isEmpty()) {
            return new ArrayList<>();
          }
          Set<Integer> editionIds = new HashSet<>();
          for (Author a : authorList) {
            editionIds.addAll(dao.getFirst(a.getId()));
          }
          if (editionIds.isEmpty()) {
            return new ArrayList<>();
          }
          authorEditions = new ArrayList<>();
          for (int i : editionIds) {
            authorEditions.add(dao.get(i));
          }
          if (authorEditions.isEmpty()) {
            return new ArrayList<>();
          }
        }
      }
      List<Edition> finalEditions = null;
      if (titleEditions == null) {
        finalEditions = authorEditions;
      }
      if (authorEditions == null) {
        finalEditions = titleEditions;
      }
      if (titleEditions != null && authorEditions != null) {
        finalEditions = new ArrayList<>();
        for (Edition edition : titleEditions) {
          if (authorEditions.contains(edition)) {
            finalEditions.add(edition);
          }
        }
        if (finalEditions.isEmpty()) {
          return new ArrayList<>();
        }
      }
      return finalEditions;
    } catch (DaoException e) {
      throw new ServiceException("Cannot find books", e);
    }
  }

  @Override
  public Book findFreeBookByEdition(int id) throws ServiceException {
    Book book = null;
    try {
      List<Book> result = findBooksByEdition(id);
      for (Book b : result) {
        System.out.println(b.fieldForName(Book.RESERVED).getValue());
        if (!b.fieldForName(Book.LOCATION_ID)
                .getValue()
                .equals(ConstantManager.LOCATION_DECOMMISSIONED)
            && !b.fieldForName(Book.LOCATION_ID).getValue().equals(ConstantManager.LOCATION_ON_HAND)
            && !b.fieldForName(Book.RESERVED).getValue().equals(ConstantManager.RESERVED)
            && !b.fieldForName(Book.RESERVED).getValue().equals(ConstantManager.PREPARED)) {
          book = b;
          break;
        }
      }
    } catch (LibraryEntityException e) {
      throw new ServiceException("Cannot find books", e);
    }
    return book;
  }

  @Override
  public void bindAuthors(EditionInfo info) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      ManyToManyDao<Edition, Author> editionDao = new ManyToManyDaoImpl(connector);
      LibraryDao<Author> authorDao = new LibraryDaoImpl<>(TableEntityMapper.AUTHOR, connector);
      Set<Author> authors = new HashSet<>();
      // TODO start transaction
      Set<Integer> authorIds = editionDao.getSecond(info.getEdition().getId());
      for (int id : authorIds) {
        authors.add(authorDao.get(id));
      }
      // TODO finish transaction
      StringBuilder stringBuilder = new StringBuilder();
      for (Author a : authors) {
        stringBuilder.append(a.fieldForName(Author.SURNAME).getValue()).append(" ");
      }
      info.setAuthors(stringBuilder.toString());

    } catch (DaoException | LibraryEntityException e) {
      throw new ServiceException("Cannot find relevant books", e);
    }
  }

  @Override
  public void bindBookAndLocation(EditionInfo info) throws ServiceException {
    try {
      Book book = findFreeBookByEdition(info.getEdition().getId());
      info.setBook(book);
      if (book != null) {
        int locationId = (int) book.fieldForName(Book.LOCATION_ID).getValue();
        info.setLocationId(locationId);
      }
    } catch (ServiceException | LibraryEntityException e) {
      throw new ServiceException("Cannot find relevant books", e);
    }
  }

  @Override
  public void addCode(String code) throws ServiceException {}

  @Override
  public int editionIdByCode(String code) throws ServiceException {
    return 0;
  }

  @Override
  public void addBook(Book book) throws ServiceException {}

  @Override
  public int addEdition(Edition edition) throws ServiceException {
    return 0;
  }

  @Override
  public int addAuthor(Author author) throws ServiceException {
    return 0;
  }

  @Override
  public void bindEditionAndAuthors(int editionId, int[] authorsId) throws ServiceException {}

  @Override
  public List<Author> findAuthors(Criteria criterion) throws ServiceException {
    return null;
  }

  @Override
  public void decommissionBook(int bookId) throws ServiceException {}

  @Override
  public List<User> findUsers(String surname, String name) throws ServiceException {
    return null;
  }

  @Override
  public void addUser(User user) throws ServiceException {}

  @Override
  public void changeStatus(int userId, int roleId) throws ServiceException {}

  @Override
  public void orderBook(BookOrder bookOrder) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> bookDao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      int userId = bookOrder.getUserId();
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        Book book = editionInfo.getBook();
        int bookId = book.getId();
        EntityField<Integer> userField = new EntityField<>(Book.READER_ID);
        userField.setValue(userId);
        EntityField<Integer> reserveField = new EntityField<>(Book.RESERVED);
        reserveField.setValue(ConstantManager.RESERVED);
        try {
          connector.suspendAutoCommit();
          Book dBook = bookDao.get(bookId);
          if (dBook.fieldForName(Book.RESERVED).getValue().equals(ConstantManager.NOT_RESERVED)) {
            bookDao.update(bookId, userField);
            bookDao.update(bookId, reserveField);
          }
          connector.commit();
        } catch (SQLException | LibraryEntityException throwables) {
          connector.rollback();
        } finally {
          connector.confirmAutoCommit();
        }
      }
    } catch (DaoException | SQLException e) {
      throw new ServiceException("Cannot order book", e);
    }
  }

  @Override
  public void prepareOrder(BookOrder bookOrder) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> bookDao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        Book book = editionInfo.getBook();
        int bookId = book.getId();
        EntityField<Integer> locField = new EntityField<>(Book.LOCATION_ID);
        locField.setValue(ConstantManager.LOCATION_READING_HALL_RESERVE);
        EntityField<Integer> reserveField = new EntityField<>(Book.RESERVED);
        reserveField.setValue(ConstantManager.PREPARED);
        try {
          connector.suspendAutoCommit();
          bookDao.update(bookId, locField);
          bookDao.update(bookId, reserveField);
          connector.commit();
        } catch (SQLException throwables) {
          connector.rollback();
        } finally {
          connector.confirmAutoCommit();
        }
      }
    } catch (DaoException | SQLException e) {
      throw new ServiceException("Cannot prepare book", e);
    }
  }

  public void denyOrder(BookOrder bookOrder) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> bookDao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      for (EditionInfo editionInfo : bookOrder.getEditionInfoSet()) {
        Book book = editionInfo.getBook();
        int bookId = book.getId();
        EntityField<Integer> reserveField = new EntityField<>(Book.RESERVED);
        reserveField.setValue(ConstantManager.NOT_RESERVED);
        EntityField<Integer> userField = new EntityField<>(Book.READER_ID);
        userField.setValue(null);
        try {
          connector.suspendAutoCommit();
          bookDao.update(bookId, reserveField);
          bookDao.update(bookId, userField);
          connector.commit();
        } catch (SQLException throwables) {
          connector.rollback();
        } finally {
          connector.confirmAutoCommit();
        }
      }
    } catch (DaoException | SQLException e) {
      throw new ServiceException("Cannot deny book", e);
    }
  }

  @Override
  public void fulfillOrder(BookOrder dispatchedOrder) throws ServiceException {
    try (DBConnector connector = DBConnectorPool.getInstance().obtainConnector()) {
      LibraryDao<Book> bookDao = new LibraryDaoImpl<>(TableEntityMapper.BOOK, connector);
      for (EditionInfo editionInfo : dispatchedOrder.getEditionInfoSet()) {
        Book book = editionInfo.getBook();
        int bookId = book.getId();
        EntityField<Integer> locField = new EntityField<>(Book.LOCATION_ID);
        locField.setValue(ConstantManager.LOCATION_ON_HAND);
        EntityField<Integer> reserveField = new EntityField<>(Book.RESERVED);
        reserveField.setValue(ConstantManager.ISSUED);
        try {
          connector.suspendAutoCommit();
          bookDao.update(bookId, locField);
          bookDao.update(bookId, reserveField);
          connector.commit();
        } catch (SQLException throwables) {
          connector.rollback();
        } finally {
          connector.confirmAutoCommit();
        }
      }
    } catch (DaoException | SQLException e) {
      throw new ServiceException("Cannot prepare book", e);
    }
  }

  @Override
  public Book findBookById(int bookId) throws ServiceException {
    return null;
  }

  @Override
  public void fixReturn(Book book) throws ServiceException {}

  @Override
  public Collection<BookOrder> getPlacedOrder() throws ServiceException {
    return null;
  }

  @Override
  public Collection<BookOrder> getPreparedOrders() {
    return null;
  }
}
