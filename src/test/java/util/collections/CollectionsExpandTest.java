package util.collections;

import static util.collections.Collections.*;

import org.junit.Test;

public class CollectionsExpandTest {
  @Test
  public void a() {
    for (int i : range(0, 10))
      System.out.println(i);
  }
}
