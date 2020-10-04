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
    EntityField<Boolean> forDelivery = new EntityField<>("forDelivery");
    EntityField<Date> dueDate = new EntityField<>("dueDate");
    return new EntityField[] {editionId, forDelivery, locationId, readerId, dueDate,
            standardLocationId};
  }

}
