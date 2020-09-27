package by.pavka.model.dao.impl;

import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SimpleSqlLibraryDao;
import by.pavka.entity.impl.Operation;

public class OperationLibraryDao extends SimpleSqlLibraryDao<Operation> {

  public OperationLibraryDao() throws DaoException {}

  @Override
  protected String getTableName() {
    return "operations";
  }

  @Override
  public Operation createEntity() {
    return new Operation();
  }
}
