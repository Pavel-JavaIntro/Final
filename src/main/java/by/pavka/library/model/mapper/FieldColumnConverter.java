package by.pavka.library.model.mapper;

import by.pavka.library.entity.criteria.EntityField;

public interface FieldColumnConverter {
  public String formColumnName(EntityField<?> field);
  public String formFieldName(String column);
}
