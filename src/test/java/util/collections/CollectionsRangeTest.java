package util.collections;

import static flute.util.collections.Collections.*;

import org.junit.Test;

public class CollectionsRangeTest {
  @Test
  public void a() {
    for (int i : range(0, 10))
      System.out.println(i);
  }
}
