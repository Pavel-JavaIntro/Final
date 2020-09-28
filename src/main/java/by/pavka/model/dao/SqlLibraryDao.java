package by.pavka.model.dao;

import by.pavka.model.ConnectionWrapper;
import by.pavka.entity.criteria.Criteria;
import by.pavka.entity.EntityFactory;
import by.pavka.entity.criteria.EntityField;
import by.pavka.entity.LibraryEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlLibraryDao<T extends LibraryEntity>
    implements LibraryDao<T>, EntityFactory<T> {
  private static final String LIST_ALL = "SELECT * FROM ";
  private static final String DELETE = "DELETE FROM %s WHERE id=?";
  private static final String INSERT = "INSERT INTO %s ";
  private static final String UPDATE = "UPDATE %s %s WHERE id=?";
  private ConnectionWrapper connector;

  public SqlLibraryDao() throws DaoException {
    connector = new ConnectionWrapper();
  }

  public ConnectionWrapper getConnector() {
    return connector;
  }

  @Override
  public void update(int id, EntityField<?>... fields) throws DaoException {
    String sql = String.format(UPDATE, getTableName(), interpret(fields));
    ConnectionWrapper connector = getConnector();
    PreparedStatement statement = null;
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleSqlLibraryDao exception", e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  @Override
  public List<T> read() throws DaoException {
    return list(LIST_ALL + getTableName());
  }

  @Override
  public List<T> read(Criteria criteria) throws DaoException {
    return list(LIST_ALL + getTableName() + interpret(criteria));
  }

  @Override
  public void remove(int id) throws DaoException {
    PreparedStatement statement = null;
    String sql = String.format(DELETE, getTableName());
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("Exception in class " + getClass().getSimpleName(), e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  @Override
  public void add(T entity) throws DaoException {
    PreparedStatement statement = null;
    String sql = String.format(INSERT, getTableName());
    try {
      sql += formAddRequest(entity);
      statement = connector.obtainPreparedStatement(sql);
      System.out.println(statement);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SqlLibraryDao exception", e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  protected abstract String getTableName();

  @Override
  public void close() {
    connector.closeConnection();
  }

  private List<T> list(String sql) throws DaoException {
    List<T> items = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet;
    try {
      statement = connector.obtainPreparedStatement(sql);
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        T item = formEntity(resultSet);
        items.add(item);
      }
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleSqlLibraryDao exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return items;
  }

  protected abstract T formEntity(ResultSet resultSet) throws SQLException;

  protected abstract String formAddRequest(T entity);

  private String interpret(Criteria criteria) {
    if (criteria == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder(" WHERE ");
    for (int i = 0; i < criteria.size(); i++) {
      EntityField<?> field = criteria.getConstraint(i);
      builder.append(field.getName());
      if (field.getValue() instanceof String) {
        builder.append(" LIKE ").append(field.getValue()).append('%');
      } else {
        builder.append("=").append(field.getValue());
      }
      if (i < criteria.size() - 1) {
        builder.append(" AND ");
      }
    }
    return builder.toString();
  }

  private String interpret(EntityField<?>... fields) {
    StringBuilder builder = new StringBuilder(" SET ");
    for (int i = 0; i < fields.length; i++) {
      EntityField<?> field = fields[i];
      builder.append(field.getName());
      builder.append("=");
      builder.append(field.getValue());
      if (i < fields.length - 1) {
        builder.append(", ");
      }
    }
    return builder.toString();
  }
}
