package by.pavka.library.model.dao.impl;

import by.pavka.library.entity.EntityExtractor;
import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.LibraryEntityException;
import by.pavka.library.entity.SimpleListEntity;
import by.pavka.library.entity.criteria.Criteria;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.model.ConnectionWrapper;
import by.pavka.library.model.dao.LibraryDao;
import by.pavka.library.model.mapper.ColumnFieldMapper;
import by.pavka.library.model.mapper.TableEntityMapper;
import by.pavka.library.model.dao.DaoException;
import by.pavka.library.model.mapper.converter.ConverterFactory;
import by.pavka.library.model.mapper.converter.FieldColumnConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleLibraryDao<T extends LibraryEntity> implements LibraryDao<T>, EntityExtractor<T>, AutoCloseable {

  private static final String INSERT = "INSERT INTO %s ";
  private static final String LIST_ALL = "SELECT * FROM ";
  private static final String GET = "SELECT * FROM %s WHERE id=?";
  private static final String UPDATE = "UPDATE %s %s WHERE id=?";
  private static final String DELETE = "DELETE FROM %s WHERE id=?";

  private final String tableName;
  private final EntityExtractor<T> entityExtractor;
  private ConnectionWrapper connector;

  public SimpleLibraryDao(TableEntityMapper mapper) throws DaoException {
    tableName = mapper.getTableName();
    entityExtractor = mapper.getExtractor();
    connector = new ConnectionWrapper();
  }

  public ConnectionWrapper getConnector() {
    return connector;
  }

  @Override
  public void add(T entity) throws DaoException {
    PreparedStatement statement = null;
    String sql = String.format(INSERT, getTableName());
    System.out.println(sql);
    try {
      sql += formInsertRequest(entity);
      System.out.println(sql);
      statement = connector.obtainPreparedStatement(sql);
      statement.executeUpdate();
      System.out.println(statement);
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleLibraryDao add exception", e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  @Override
  public List<T> read() throws DaoException {
    return list(LIST_ALL + getTableName());
  }

  @Override
  public List<T> read(Criteria criteria, boolean strict) throws DaoException {
    System.out.println(LIST_ALL + getTableName() + interpret(criteria, strict));
    return list(LIST_ALL + getTableName() + interpret(criteria, strict));
  }

  @Override
  public T get(int id) throws DaoException {
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql = String.format(GET, getTableName());
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return formEntity(resultSet);
      } else {
        return null;
      }
    } catch (DaoException | SQLException | LibraryEntityException e) {
      throw new DaoException("SimpleLibraryDao remove excdeption", e);
    } finally {
      connector.closeStatement(statement);
    }
  }


  @Override
  public void update(int id, EntityField<?>... fields) throws DaoException {
    if (fields.length != 1 || !fields[0].getName().equals(SimpleListEntity.COLUMN_NAME)) {
      throw new DaoException("Wrong Update request");
    }
    PreparedStatement statement = null;
    String sql = String.format(UPDATE, getTableName(), SimpleListEntity.COLUMN_NAME);
    try {
      statement = connector.obtainPreparedStatement(sql);
      statement.setString(1, (String) (fields[0].getValue()));
      statement.setInt(2, id);
      statement.executeUpdate();
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleLibraryDao update exception", e);
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
      throw new DaoException("SimpleLibraryDao remove excdeption", e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  @Override
  public boolean contains(Criteria criteria, boolean strict) throws DaoException {
    PreparedStatement statement = null;
    ResultSet resultSet;
    String sql = LIST_ALL + getTableName() + interpret(criteria, strict);
    System.out.println("SQL = " + sql);
    try {
      statement = connector.obtainPreparedStatement(sql);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return true;
      }
      return false;
    } catch (DaoException | SQLException e) {
      throw new DaoException("SimpleLibraryDao contains exception", e);
    } finally {
      connector.closeStatement(statement);
    }
  }

  @Override
  public void close() {
    connector.closeConnection();
  }

  private String getTableName() {
    return tableName;
  }

  @Override
  public T extractEntity() {
    return entityExtractor.extractEntity();
  }

  private String interpret(Criteria criteria, boolean strict) {
    if (criteria == null) {
      return "";
    }
    StringBuilder builder = new StringBuilder(" WHERE ");
    FieldColumnConverter converter = ConverterFactory.getInstance().getConverter();
    for (int i = 0; i < criteria.size(); i++) {
      EntityField<?> field = criteria.getConstraint(i);
      builder.append(converter.formColumnName(field));
      if (!strict && field.getValue() instanceof String) {
        builder.append(" LIKE '%").append(field.getValue()).append("%'");
      } else {
        builder.append("='").append(field.getValue()).append("'");
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
    } catch (DaoException | SQLException | LibraryEntityException e) {
      throw new DaoException("SimpleLibraryDao list exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return items;
  }

  private T formEntity(ResultSet resultSet) throws SQLException, LibraryEntityException {
    T item = extractEntity();
    int id = resultSet.getInt("id");
    item.setId(id);
    ResultSetMetaData metaData = resultSet.getMetaData();
    int count = metaData.getColumnCount();;
    ColumnFieldMapper<T> mapper = ColumnFieldMapper.getInstance(item);
    for (int i = 2; i <= count; i++) {
      String name = metaData.getColumnName(i);
      String fieldName = mapper.getFieldName(name);
      item.setValue(fieldName, resultSet.getObject(i));
    }
    return item;
  }

  private String formInsertRequest(T entity) {
    StringBuilder template = new StringBuilder("(");
    StringBuilder values = new StringBuilder(" VALUES (");
    ColumnFieldMapper<T> mapper = ColumnFieldMapper.getInstance(entity);
    for (EntityField field : entity.getFields()) {
      if (field.getValue() == null) {
        continue;
      }
      template.append(mapper.getColumnName(field)).append(",");
      values.append("'").append(field.getValue()).append("',");
    }
    template.replace(template.length() - 1, template.length(), ")");
    values.replace(values.length() - 1, values.length(), ")");
    String result = template.toString() + values.toString();
    return result;
  }
}
