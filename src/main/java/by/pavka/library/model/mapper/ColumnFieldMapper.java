package by.pavka.library.model.mapper;

import by.pavka.library.entity.impl.*;
import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.model.LibraryFatalException;
import by.pavka.library.model.mapper.converter.ConverterFactory;
import by.pavka.library.model.mapper.converter.FieldColumnConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ColumnFieldMapper<T extends LibraryEntity> {
  private static final Logger LOGGER = LogManager.getLogger(ColumnFieldMapper.class.getName());
  private static final ColumnFieldMapper<Location> locationMapper = new ColumnFieldMapper<>(new Location());
  private static final ColumnFieldMapper<Genre> genreMapper = new ColumnFieldMapper<>(new Genre());
  private static final ColumnFieldMapper<Role> roleMapper = new ColumnFieldMapper<>(new Role());
  private static final ColumnFieldMapper<Operation> operationMapper = new ColumnFieldMapper<>(new Operation());
  private static final ColumnFieldMapper<Author> authorMapper = new ColumnFieldMapper<>(new Author());
  private static final ColumnFieldMapper<Edition> editionMapper = new ColumnFieldMapper<>(new Edition());
  private static final ColumnFieldMapper<Book> bookMapper = new ColumnFieldMapper<>(new Book());
  private static final ColumnFieldMapper<User> userMapper = new ColumnFieldMapper<>(new User());
  private static final ColumnFieldMapper<Transaction> transactionMapper = new ColumnFieldMapper<>(new Transaction());

  private final Map<String, String> mapper = new HashMap<>();

  private ColumnFieldMapper(T entity) {
    FieldColumnConverter converter = ConverterFactory.getInstance().getConverter();
    for (EntityField field : entity.getFields()) {
      mapper.put(field.getName(), converter.formColumnName(field));
    }
  }

  public static <T> ColumnFieldMapper getInstance(T entity) {
    if (entity instanceof Location) {
      return locationMapper;
    }
    if (entity instanceof Genre) {
      return genreMapper;
    }
    if (entity instanceof Role) {
      return roleMapper;
    }
    if (entity instanceof Operation) {
      return operationMapper;
    }
    if (entity instanceof Author) {
      return authorMapper;
    }
    if (entity instanceof Edition) {
      return editionMapper;
    }
    if (entity instanceof Book) {
      return bookMapper;
    }
    if (entity instanceof User) {
      return userMapper;
    }
    if (entity instanceof Transaction) {
      return transactionMapper;
    }
    LOGGER.fatal("Mapper doesn't include this instance");
    throw new LibraryFatalException("Mapper doesn't include this instance");
  }

  public String getColumnName(EntityField field) {
    return mapper.get(field.getName());
  }

  public String getFieldName(String columnname) {
    for (Map.Entry<String, String> entry : mapper.entrySet()) {
      if (entry.getValue().equals(columnname))
        return entry.getKey();
    }
    LOGGER.fatal("ColumnFieldMapper doesn't work");
    throw new LibraryFatalException("ColumnFieldMapper doesn't work");
  }

}
