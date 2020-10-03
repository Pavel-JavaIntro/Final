package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

import java.util.Date;

public class Transaction extends LibraryEntity {
  @Override
  public EntityField[] listFields() {
    EntityField<Integer> operationId = new EntityField<>("operation_id");
    EntityField<Integer> actorId = new EntityField<>("actor_id");
    EntityField<Integer> userId = new EntityField<>("user_id");
    EntityField<Integer> bookId = new EntityField<>("book_id");
    EntityField<Integer> locationId = new EntityField<>("location_id");
    EntityField<Date> datetime = new EntityField<>("datetime");
    return new EntityField[] {operationId, actorId, userId, bookId, locationId, datetime};
  }
}
