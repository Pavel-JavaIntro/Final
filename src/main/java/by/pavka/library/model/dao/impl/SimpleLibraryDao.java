package by.pavka.library.model.dao.impl;

import by.pavka.library.entity.EntityFactory;
import by.pavka.library.entity.SimpleEntity;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.model.ConnectionWrapper;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.mapper.ColumnFieldMapper;
import by.pavka.library.model.mapper.TableEntityMapper;
import by.pavka.library.model.dao.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleLibraryDao<T extends SimpleEntity> implements LibraryDao<T>, EntityFactory<T> {

  private static final String INSERT = "INSERT INTO %s ";
  private static final String LIST_ALL = "SELECT * FROM ";
  private static final String UPDATE = "UPDATE %s %s WHERE id=?";
  private static final String DELETE = "DELETE FROM %s WHERE id=?";

  private final String tableName;
  private final EntityFactory<T> entityFactory;
  private ConnectionWrapper connector;

  public SimpleLibraryDao(TableEntityMapper mapper) throws DaoException {
    tableName = mapper.getTableName();
    entityFactory = mapper.getFactory();
    connector = new ConnectionWrapper();
  }

  @Override
  public void add(T entity) throws DaoException {
    PreparedStatement statement = null;
    String sql = String.format(INSERT, getTableName());
    try {
      sql += formInsertRequest(entity);
      statement = connector.obtainPreparedStatement(sql);
      System.out.println(statement);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SqlLibraryDao exception", e);
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
  public void update(int id, EntityField<?>... fields) throws DaoException {
    if (fields.length != 1 || !fields[0].getName().equals(SimpleEntity.COLUMN_NAME)) {
      throw new DaoException("Wrong Update request");
    }
    PreparedStatement statement = null;
    String sql = String.format(UPDATE, getTableName(), SimpleEntity.COLUMN_NAME);
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setString(1, (String) (fields[0].getValue()));
      statement.setInt(2, id);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleLibraryDao exception", e);
    } finally {
      connector.closeStatement(statement);
    }
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
  public void close() {
    connector.closeConnection();
  }

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

  protected String getTableName() {
    return tableName;
  }

  protected T formEntity(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    String description = resultSet.getString(SimpleEntity.COLUMN_NAME);
    T item = createEntity();
    item.setId(id);
    item.setValue(SimpleEntity.COLUMN_NAME, description);
    return item;
  }

  private String formInsertRequest(T entity) {
    StringBuilder template = new StringBuilder("(");
    StringBuilder values = new StringBuilder(" VALUES (");
    ColumnFieldMapper<T> mapper = ColumnFieldMapper.getInstance(entity);
    for (EntityField field : entity.getFields()) {
      template.append(mapper.getColumnName(field)).append(",");
      values.append("'").append(field.getValue()).append("',");
    }
    template.replace(template.length() - 1, template.length(), ")");
    values.replace(values.length() - 1, values.length(), ")");
    String result = template.toString() + values.toString();
    return result;
  }

  @Override
  public T createEntity() {
    return entityFactory.createEntity();
  }
}
