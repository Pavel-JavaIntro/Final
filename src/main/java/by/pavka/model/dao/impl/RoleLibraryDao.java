package by.pavka.model.dao.impl;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SimpleSqlLibraryDao;
import by.pavka.entity.impl.Role;

public class RoleLibraryDao extends SimpleSqlLibraryDao<Role> {

  public RoleLibraryDao() throws DaoException {}

  @Override
  protected String getTableName() {
    return "roles";
  }

  @Override
  public Role createEntity() {
    return new Role();
  }
}
