package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

public class Author extends LibraryEntity {
  @Override
  public EntityField[] listFields() {
    EntityField<String> surname = new EntityField<>("surname");
    EntityField<String> firstName = new EntityField<>("firstName");
    EntityField<String> secondName = new EntityField<>("secondName");
    return new EntityField[] {surname, firstName, secondName};
  }
}
