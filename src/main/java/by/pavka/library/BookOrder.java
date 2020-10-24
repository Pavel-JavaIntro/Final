package by.pavka.library;

import by.pavka.library.entity.EditionInfo;
import by.pavka.library.entity.client.AppClient;

import java.util.HashSet;
import java.util.Set;

public class BookOrder {
  private final int userId;
  private final Set<EditionInfo> editionInfoSet;

  public BookOrder(AppClient client) {
    this.userId = client.getId();
    editionInfoSet = new HashSet<>(client.getEditionInfos());
    client.getEditionInfos().clear();
  }

  public BookOrder(int id, EditionInfo editionInfo) {
    userId = id;
    editionInfoSet = new HashSet<>();
    editionInfoSet.add(editionInfo);
  }

  public int getUserId() {
    return userId;
  }

  public Set<EditionInfo> getEditionInfoSet() {
    return editionInfoSet;
  }

  public BookOrder passBook(EditionInfo editionInfo) {
    editionInfoSet.remove(editionInfo);
    return new BookOrder(userId, editionInfo);
  }

  public void addBook(EditionInfo editionInfo) {
    editionInfoSet.add(editionInfo);
  }

  public boolean isFulfilled() {
    return editionInfoSet.isEmpty();
  }
}
