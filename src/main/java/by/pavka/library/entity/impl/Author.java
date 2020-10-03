package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

public class Author extends LibraryEntity {
  @Override
  public EntityField[] listFields() {
    EntityField<String> surname = new EntityField<>("surname");
    EntityField<String> firsName = new EntityField<>("first_name");
    EntityField<String> secondName = new EntityField<>("second_name");
    return new EntityField[] {surname, firsName, secondName};
  }
}
