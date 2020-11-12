package by.pavka.library.entity.impl;

import by.pavka.library.entity.criteria.EntityField;
import by.pavka.library.entity.LibraryEntity;

import java.util.Date;

public class Book extends LibraryEntity {

  @Override
  public EntityField[] listFields() {
    EntityField<Integer> editionId = new EntityField<>("editionId");
    EntityField<Integer> locationId = new EntityField<>("locationId");
    EntityField<Integer> readerId = new EntityField<>("readerId");
    EntityField<Integer> standardLocationId = new EntityField<>("standardLocationId");
    EntityField<Integer> reserved = new EntityField<>("reserved");
    EntityField<Date> dueDate = new EntityField<>("dueDate");
    return new EntityField[] {editionId, reserved, locationId, readerId, dueDate,
            standardLocationId};
  }

}
