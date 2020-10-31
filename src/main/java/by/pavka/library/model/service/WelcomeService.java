package by.pavka.library.model.service;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.SimpleListEntity;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Book;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.entity.impl.User;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.dao.ManyToManyDao;
import by.pavka.library.model.dao.impl.LibraryDaoFactory;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.model.mapper.TableEntityMapper;

import java.util.*;

public class WelcomeService {
  private static WelcomeService instance = new WelcomeService();

  private WelcomeService() {}

  public static WelcomeService getInstance() {
    return instance;
  }

  public <T extends SimpleListEntity> void initConstants(
      Map<Integer, String> constants, TableEntityMapper constant) throws ServiceException {
    List<T> list = null;
    LibraryDao<T> dao = null;
    try {
      dao = LibraryDaoFactory.getInstance().obtainDao(constant);
      list = dao.read();
      // dao.close();
    } catch (DaoException e) {
      throw new ServiceException("Cannot initialize constants", e);
    } finally {
      if (dao != null) {
        dao.close();
      }
    }
    for (T entity : list) {
      constants.put(entity.getId(), entity.getDescription());
    }
  }

  public int countBooks() throws ServiceException {
    try (LibraryDao<Book> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.BOOK)) {
      return dao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the books", e);
    }
  }

  public int countUsers() throws ServiceException {
    try (LibraryDao<User> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.USER)) {
      return dao.read().size() - 1;
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the books", e);
    }
  }

  public User auth(String surname, String name, String password) throws ServiceException {
    int hashPass = password.hashCode();
    try (LibraryDao<User> dao = LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.USER)) {
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

  public List<Book> findBooksByEditionCode(String code) throws ServiceException {
    List<Book> books;
    try (LibraryDao<Edition> editionDao =
        LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.EDITION)) {
      EntityField<String> field = new EntityField<>("description");
      field.setValue(code);
      Criteria criteria = new Criteria();
      criteria.addConstraint(field);
      List<Edition> editions = editionDao.read(criteria, true);
      int editionId = 0;
      if (!editions.isEmpty()) {
        editionId = editions.get(0).getId();
      }
      books = findBooksByEdition(editionId);
    } catch (DaoException e) {
      throw new ServiceException("Cannot find editions", e);
    }
    return books;
  }

  public List<Book> findBooksByEdition(int id) throws ServiceException {
    List<Book> result = new ArrayList<>();
    try (LibraryDao<Book> bookDao =
             LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.BOOK)) {
      Criteria criteria = new Criteria();
      EntityField<Integer> edId = new EntityField<>("editionId");
      edId.setValue(id);
      criteria.addConstraint(edId);
      result.addAll(bookDao.read(criteria, true));
    } catch (DaoException e) {
      throw new ServiceException("Cannot find books", e);
    }
    return result;
  }

  public List<Edition> findEditions(String title, String author) throws ServiceException {
    try (ManyToManyDao<Edition, Author> editionDao =
        LibraryDaoFactory.getInstance().obtainManyToManyDao()) {
      if (title.isEmpty() && author.isEmpty()) {
        return editionDao.read();
      }

      List<Edition> titleEditions = null;
      List<Edition> authorEditions = null;

      if (!title.isEmpty()) {
        Criteria criteriaT = new Criteria();
        EntityField<String> titleField = new EntityField<>("title");
        titleField.setValue(title);
        criteriaT.addConstraint(titleField);
        titleEditions = editionDao.read(criteriaT, false);
        if (titleEditions.isEmpty()) {
          return new ArrayList<>();
        }
      }

      List<Author> authorList = null;
      if (!author.isEmpty()) {
        try (LibraryDao<Author> authorDao =
            LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.AUTHOR)) {
          Criteria criteriaA = new Criteria();
          EntityField<String> authorField = new EntityField<>("surname");
          authorField.setValue(author);
          criteriaA.addConstraint(authorField);
          authorList = authorDao.read(criteriaA, false);
          if (authorList.isEmpty()) {
            return new ArrayList<>();
          }
          Set<Integer> editionIds = new HashSet<>();
          for (Author a : authorList) {
            editionIds.addAll(editionDao.getFirst(a.getId()));
          }
          if (editionIds.isEmpty()) {
            return new ArrayList<>();
          }
          authorEditions = new ArrayList<>();
          for (int i : editionIds) {
            authorEditions.add(editionDao.get(i));
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

  public Book findBookByEdition(int id) throws ServiceException {
    Book book = null;
    try (LibraryDao<Book> bookDao =
        LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.BOOK)) {
      List<Book> result = findBooksByEdition(id);
//      List<Book> result = new ArrayList<>();
//      Criteria criteria = new Criteria();
//      EntityField<Integer> edId = new EntityField<>("editionId");
//      edId.setValue(id);
//      criteria.addConstraint(edId);
//      result.addAll(bookDao.read(criteria, true));
      for (Book b : result) {
        if (!b.fieldForName("locationId").getValue().equals(ConstantManager.LOCATION_DECOMISSIONED)
            && !b.fieldForName("locationId").getValue().equals(ConstantManager.LOCATION_ON_HAND)) {
          book = b;
          break;
        }
      }
    } catch (DaoException | LibraryEntityException e) {
      throw new ServiceException("Cannot find books", e);
    }
    return book;
  }

  public void bindAuthors(EditionInfo info) throws ServiceException {
    try (ManyToManyDao<Edition, Author> editionDao =
            LibraryDaoFactory.getInstance().obtainManyToManyDao();
        LibraryDao<Author> authorDao =
            LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.AUTHOR)) {

      Set<Author> authors = new HashSet<>();
      Set<Integer> authorIds = editionDao.getSecond(info.getEdition().getId());
      for (int id : authorIds) {
        authors.add(authorDao.get(id));
      }
      StringBuilder stringBuilder = new StringBuilder();
      for (Author a : authors) {
        stringBuilder.append(a.fieldForName("surname").getValue()).append(" ");
      }
      info.setAuthors(stringBuilder.toString());

    } catch (DaoException | LibraryEntityException e) {
      throw new ServiceException("Cannot find relevant books", e);
    }
  }

  public void bindBookAndLocation(EditionInfo info) throws ServiceException {
    try {
      Book book = findBookByEdition(info.getEdition().getId());
      info.setBook(book);
      if (book != null) {
        int locationId = (int) book.fieldForName("locationId").getValue();
        info.setLocationId(locationId);
      }
    } catch (ServiceException | LibraryEntityException e) {
      throw new ServiceException("Cannot find relevant books", e);
    }
  }
}
