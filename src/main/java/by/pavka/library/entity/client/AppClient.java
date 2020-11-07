package by.pavka.library.entity.client;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.model.mapper.ConstantManager;

import java.io.Serializable;
import java.util.*;

public abstract class AppClient implements Serializable {
  private int id;
  private String surname;
  private String name;
  private String email;
  private int reservedBooks;
  private Set<EditionInfo> editioninfos = new HashSet<>();

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
    return editioninfos;
  }

  public boolean addEditionInfo(EditionInfo info) {
    if (info.getBook() == null) {
      return false;
    }
    boolean result = editioninfos.add(info);
    if (result) {
      reservedBooks++;
    }
    return result;
  }

  public int getBasketSize() {
    return editioninfos.size();
  }

  public void removeEditionInfo(EditionInfo info) {
    editioninfos.remove(info);
    reservedBooks--;
  }

  public abstract String getRole();

  public String getEntrance() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        System.out.println("INSIDE VISITOR1");
        return "block/login.jsp";
      default:
        System.out.println("INSIDE ADMIN1");
        return "block/logout.jsp";
    }
  }

  public String getAdminSection() {
    switch (getRole()) {
      case ConstantManager.ADMIN:
      case ConstantManager.LIBRARIAN:
        return "block/admin.jsp";
      default:
        return "block/latin.jsp";
    }
  }

  public String getBookSelection() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        return null;
      case ConstantManager.READER:
        return "block/reader_select.jsp";
      default:
        return "block/subscriber_select.jsp";
    }
  }

  public String getBasket() {
    switch (getRole()) {
      case ConstantManager.GUEST:
        return null;
      default:
        return "block/basket.jsp";
    }
  }

  public String getAssignment() {
    switch (getRole()) {
      case ConstantManager.ADMIN:
        return "admin_assign.jsp";
      default:
        return "librarian_assign.jsp";
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
