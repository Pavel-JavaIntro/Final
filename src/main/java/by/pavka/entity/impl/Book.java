package by.pavka.entity.impl;

import by.pavka.entity.LibraryEntity;

import java.util.Date;

public class Book extends LibraryEntity {
  private final int editionId;
  private boolean forDelivery;
  private int locationId;
  private int readerId;
  private Date dueDate;
  private int standardLocationId;

  public Book(int editionId, boolean forDelivery, int locationId, int readerId, Date dueDate, int standardLocationId) {
    this.editionId = editionId;
    this.forDelivery = forDelivery;
    this.locationId = locationId;
    this.readerId = readerId;
    this.dueDate = dueDate;
    this.standardLocationId = standardLocationId;
  }

  public int getEditionId() {
    return editionId;
  }

  public boolean isForDelivery() {
    return forDelivery;
  }

  public int getLocationId() {
    return locationId;
  }

  public int getReaderId() {
    return readerId;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public int getStandardLocationId() {
    return standardLocationId;
  }
}
