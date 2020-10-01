package by.pavka.library.model.dao;

import by.pavka.library.entity.LibraryEntity;

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
