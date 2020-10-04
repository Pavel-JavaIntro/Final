package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

import java.util.Date;

public class Transaction extends LibraryEntity {
  @Override
  public EntityField[] listFields() {
    EntityField<Integer> operationId = new EntityField<>("operationId");
    EntityField<Integer> actorId = new EntityField<>("actorId");
    EntityField<Integer> userId = new EntityField<>("userId");
    EntityField<Integer> bookId = new EntityField<>("bookId");
    EntityField<Integer> locationId = new EntityField<>("locationId");
    EntityField<Date> date = new EntityField<>("date");
    return new EntityField[] {operationId, actorId, userId, bookId, locationId, date};
  }
}
