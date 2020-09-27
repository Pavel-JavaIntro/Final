package by.pavka.model.dao.impl;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SimpleSqlLibraryDao;
import by.pavka.entity.impl.Genre;

public class GenreLibraryDao extends SimpleSqlLibraryDao<Genre> {

  public GenreLibraryDao() throws DaoException {}

  @Override
  protected String getTableName() {
    return "genres";
  }

  @Override
  public Genre createEntity() {
    return new Genre();
  }
}
