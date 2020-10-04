package by.pavka.library.model.service;

import by.pavka.library.entity.impl.Book;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.impl.SimpleLibraryDao;
import by.pavka.library.model.mapper.TableEntityMapper;

public class WelcomeService {
  private static WelcomeService instance = new WelcomeService();

  private WelcomeService() {}

  public static WelcomeService getInstance() {
    return instance;
  }
  public int countBooks() throws ServiceException {
    try {
      SimpleLibraryDao<Book> dao = new SimpleLibraryDao<>(TableEntityMapper.BOOK);
      return dao.read().size();
    } catch (DaoException e) {
      throw new ServiceException("Cannot count the books", e);
    }
  }
}
