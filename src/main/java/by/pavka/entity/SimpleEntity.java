package by.pavka.entity;

import by.pavka.entity.criteria.EntityField;

public abstract class SimpleEntity extends LibraryEntity {
  public static final String COLUMN_NAME = "description";

  @Override
  public EntityField<String>[] listFields() {
    EntityField<String> description = new EntityField<>(COLUMN_NAME);
    return new EntityField[]{description};
  }

  public String getDescription() {
    return (String)(getFields()[0].getValue());
  }
}
