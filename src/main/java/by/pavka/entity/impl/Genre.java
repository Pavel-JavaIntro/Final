package by.pavka.entity.impl;

import by.pavka.entity.EntityField;
import by.pavka.entity.LibraryEntity;

public class Genre extends LibraryEntity {

  @Override
  public EntityField[] listFields() {
    EntityField<String> description = new EntityField<>("description", "Description");
    return new EntityField[] {description};
  }
}
