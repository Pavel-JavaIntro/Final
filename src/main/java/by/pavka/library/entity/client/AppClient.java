package by.pavka.library.entity.client;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.model.mapper.ConstantManager;
import by.pavka.library.newversion.PageRouter;

import java.io.Serializable;
import java.util.*;

public abstract class AppClient implements Serializable {
  private int id;
  private String surname;
  private String name;
  private String email;
  private int reservedBooks;
  private Set<EditionInfo> editionInfos = new HashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getReservedBooks() {
    return reservedBooks;
  }

  public void setReservedBooks(int reservedBooks) {
    this.reservedBooks = reservedBooks;
  }

  public Set<EditionInfo> getEditionInfos() {
    return editionInfos;
  }

  public boolean addEditionInfo(EditionInfo info) {
    if (info.getBook() == null) {
      return false;
    }
    boolean result = editionInfos.add(info);
    if (result) {
      reservedBooks++;
    }
    return result;
  }

  public int getBasketSize() {
    return editionInfos.size();
  }

  public void removeEditionInfo(EditionInfo info) {
    editionInfos.remove(info);
    reservedBooks--;
  }

  public abstract String getRole();

  public String getEntrance() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        return PageRouter.LOGIN;
      default:
        return PageRouter.LOGOUT;
    }
  }

  public String getAdminSection() {
    switch (getRole()) {
      case ConstantManager.ADMIN:
      case ConstantManager.LIBRARIAN:
        return PageRouter.ADMIN;
      default:
        return PageRouter.LATIN;
    }
  }

  public String getBookSelection() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        return null;
      case ConstantManager.READER:
        return PageRouter.READER_SELECT;
      default:
        return PageRouter.SUBSCRIBER_SELECT;
    }
  }

  public String getBasket() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        return null;
      default:
        return PageRouter.BASKET;
    }
  }

  public String getAssignment() {
    switch (getRole()) {
      case ConstantManager.ADMIN:
        return PageRouter.ADMIN_ASSIGN;
      default:
        return PageRouter.LIBRARIAN_ASSIGN;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AppClient)) return false;
    AppClient appClient = (AppClient) o;
    return surname.equals(appClient.surname)
        && name.equals(appClient.name)
        && Objects.equals(email, appClient.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(surname, name, email);
  }

  @Override
  public String toString() {
    return name + " " + surname;
  }
}
