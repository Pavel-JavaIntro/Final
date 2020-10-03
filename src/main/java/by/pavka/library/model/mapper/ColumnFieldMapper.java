package by.pavka.library.model.mapper;

import by.pavka.library.entity.impl.Genre;
import by.pavka.library.entity.impl.Location;
import by.pavka.library.entity.impl.Operation;
import by.pavka.library.entity.impl.Role;
import by.pavka.library.model.mapper.ConverterFactory;
import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.model.mapper.FieldColumnConverter;

import java.util.HashMap;
import java.util.Map;

public class ColumnFieldMapper<T extends LibraryEntity> {
  private static ColumnFieldMapper<Location> locationMapper = new ColumnFieldMapper<>(new Location());
  private static ColumnFieldMapper<Genre> genreMapper = new ColumnFieldMapper<>(new Genre());
  private static ColumnFieldMapper<Role> roleMapper = new ColumnFieldMapper<>(new Role());
  private static ColumnFieldMapper<Operation> operationMapper = new ColumnFieldMapper<>(new Operation());

  private Map<String, String> mapper = new HashMap<>();

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
    throw new AssertionError();
  }

  public String getColumnName(EntityField field) {
    return mapper.get(field.getName());
  }

  public String getFieldName(String columnname) {
    for (Map.Entry<String, String> entry : mapper.entrySet()) {
      if (entry.getValue().equals(columnname))
        return entry.getKey();
    }
    throw new AssertionError("ColumnFieldMapper doesn't work");
  }

}
