package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

public class User extends LibraryEntity {
  @Override
  public EntityField[] listFields() {
    EntityField<Integer> roleId = new EntityField<>("roleId");
    EntityField<String> surname = new EntityField<>("surname");
    EntityField<String> name = new EntityField<>("name");
    EntityField<String> address = new EntityField<>("address");
    EntityField<String> phone = new EntityField<>("phone");
    EntityField<String> email = new EntityField<>("email");
    EntityField<Integer> password = new EntityField<>("password");
    EntityField<Boolean> isBlocked = new EntityField<>("isBlocked");
    return new EntityField[] {roleId, surname, name, address, phone, email, password, isBlocked};
  }
}
