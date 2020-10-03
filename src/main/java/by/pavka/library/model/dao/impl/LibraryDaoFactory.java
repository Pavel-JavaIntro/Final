package by.pavka.library.model.dao.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.model.dao.LibraryDao;

public class LibraryDaoFactory {
  private static final LibraryDaoFactory instance = new LibraryDaoFactory();

  private LibraryDaoFactory() {}

  public static LibraryDaoFactory getInstance() {
    return instance;
  }

  public <T extends LibraryEntity> LibraryDao<T> obtainDao() {
    //TODO
    return null;
  }
}
