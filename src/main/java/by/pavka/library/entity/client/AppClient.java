package by.pavka.library.entity.client;

import by.pavka.library.entity.impl.Role;
import by.pavka.library.model.mapper.ConstantManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AppClient implements Serializable {
  private String surname;
  private String name;
  private String email;
  private List<Integer> editionIds = new ArrayList<>();

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

  public List<Integer> getEditionIds() {
    return editionIds;
  }

  public void addEdition(int id) {
    editionIds.add(id);
  }

  public int getBasketSize() {
    return editionIds.size();
  }

  public void removeEdition(int id) {
    editionIds.remove(id);
  }

  public abstract String getRole();

  public String getEntrance() {
    switch (getRole()) {
      case ConstantManager.VISITOR:
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
        System.out.println("INSIDE ADMIN2");
        return "block/admin.jsp";
      case ConstantManager.LIBRARIAN:
        return "block/admin.jsp";
      default:
        System.out.println("INSIDE VISITOR2");
        return "block/latin.jsp";
    }
  }

  public String getBookSelection() {
    switch (getRole()) {
      case ConstantManager.VISITOR:
        return null;
      default:
        return "block/reader_order.jsp";
    }
  }

  public String getBasket() {
    switch (getRole()) {
      case ConstantManager.VISITOR:
        return null;
      default:
        return "block/basket.jsp";
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
    return String.format("Уважаемый %s %s!", name, surname);
  }
}
