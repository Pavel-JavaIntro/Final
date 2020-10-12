package by.pavka.library.model.service;

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
      dao.close();
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

  public List<Book> findBooks(String title, String author) throws ServiceException {
    try (LibraryDao<Book> bookDao =
        LibraryDaoFactory.getInstance().obtainDao(TableEntityMapper.BOOK)) {
      if (title.isEmpty() && author.isEmpty()) {
        return bookDao.read();
      }
      List<Book> result = new ArrayList<>();
      List<Edition> titleEditions = null;
      List<Edition> authorEditions = null;
      try (ManyToManyDao<Edition, Author> editionDao =
          LibraryDaoFactory.getInstance().obtainManyToManyDao()) {
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
        for (Edition edition : finalEditions) {
          Criteria criteria = new Criteria();
          EntityField<Integer> edId = new EntityField<>("editionId");
          edId.setValue(edition.getId());
          criteria.addConstraint(edId);
          result.addAll(bookDao.read(criteria, true));
        }
      }
      return result;
    } catch (DaoException e) {
      throw new ServiceException("Cannot find books", e);
    }
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
}
