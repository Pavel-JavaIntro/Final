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
}
