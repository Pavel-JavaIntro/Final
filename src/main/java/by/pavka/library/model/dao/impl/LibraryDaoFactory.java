package by.pavka.library.model.dao.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.impl.Author;
import by.pavka.library.entity.impl.Edition;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.dao.ManyToManyDao;
import by.pavka.library.model.mapper.TableEntityMapper;

public class LibraryDaoFactory {
  private static final LibraryDaoFactory instance = new LibraryDaoFactory();

  private LibraryDaoFactory() {}

  public static LibraryDaoFactory getInstance() {
    return instance;
  }

  public <T extends LibraryEntity> LibraryDao<T> obtainDao(TableEntityMapper mapper)
      throws DaoException {
    return new SimpleLibraryDao<>(mapper);
  }

  public ManyToManyDao<Edition, Author> obtainManyToManyDao() throws DaoException {
    return new EditionDao();
  }
}
