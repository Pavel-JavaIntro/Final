package by.pavka.library.entity.impl;

import by.pavka.library.entity.LibraryEntity;
import by.pavka.library.entity.criteria.EntityField;

import java.util.Date;

public class Transaction extends LibraryEntity {
  public static final String OPERATION_ID = "operationId";
  public static final String ACTOR_ID = "actorId";
  public static final String USER_ID = "userId";
  public static final String BOOK_ID = "bookId";
  public static final String LOCATION_ID = "locationId";
  public static final String DATE = "date";

  @Override
  public EntityField[] listFields() {
    EntityField<Integer> operationId = new EntityField<>(OPERATION_ID);
    EntityField<Integer> actorId = new EntityField<>(ACTOR_ID);
    EntityField<Integer> userId = new EntityField<>(USER_ID);
    EntityField<Integer> bookId = new EntityField<>(BOOK_ID);
    EntityField<Integer> locationId = new EntityField<>(LOCATION_ID);
    EntityField<Date> date = new EntityField<>(DATE);
    return new EntityField[] {operationId, actorId, userId, bookId, locationId, date};
  }
}
