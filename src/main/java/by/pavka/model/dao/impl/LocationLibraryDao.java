package by.pavka.model.dao.impl;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SimpleSqlLibraryDao;
import by.pavka.entity.impl.Location;

public class LocationLibraryDao extends SimpleSqlLibraryDao<Location> {

  public LocationLibraryDao() throws DaoException {}

  @Override
  protected String getTableName() {
    return "locations";
  }

  @Override
  public Location createEntity() {
    return new Location();
  }
}
