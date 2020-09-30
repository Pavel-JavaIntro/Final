package by.pavka.model.dao.impl;

import by.pavka.entity.EntityFactory;
import by.pavka.entity.LibraryEntity;
import by.pavka.entity.SimpleEntity;
import by.pavka.entity.criteria.EntityField;
import by.pavka.model.ConnectionWrapper;
import by.pavka.model.TableEntityMapper;
import by.pavka.model.dao.DaoException;
import by.pavka.model.dao.SqlLibraryDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleLibraryDao<T extends SimpleEntity> extends SqlLibraryDao<T> {
  private static final String UPDATE = "UPDATE %s SET %s=? WHERE id=?";
  private String tableName;
  private EntityFactory entityFactory;

  public SimpleLibraryDao(String tableName) throws DaoException {
    super();
    this.tableName = tableName;
    entityFactory = TableEntityMapper.getFactory(tableName);
  }

  @Override
  public void update(int id, EntityField<?>... fields) throws DaoException {
    if (fields.length != 1 || !fields[0].getName().equals(SimpleEntity.COLUMN_NAME)) {
      throw new DaoException("Wrong Update request");
    }
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    String sql = String.format(UPDATE, getTableName(), SimpleEntity.COLUMN_NAME);
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setString(1, (String) (fields[0].getValue()));
      statement.setInt(2, id);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleSqlLibraryDao exception", e);
    } finally {
      connector.closeStatement(statement);
    }
  }


  @Override
  protected String getTableName() {
    return tableName;
  }

  @Override
  protected T formEntity(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    String description = resultSet.getString(SimpleEntity.COLUMN_NAME);
    T item = createEntity();
    item.setId(id);
    item.setValue(SimpleEntity.COLUMN_NAME, description);
    return item;
  }

  @Override
  protected String formAddRequest(T entity) {
    String description = entity.getDescription();
    return String.format(" (%s) VALUES ('%s')", SimpleEntity.COLUMN_NAME, description);
  }

  @Override
  public T createEntity() {
    return (T)entityFactory.createEntity();
  }
}
