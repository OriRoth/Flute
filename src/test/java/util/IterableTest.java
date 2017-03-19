package util;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static util.IterableWrapper.combine;
import static util.IterableWrapper.iterable;

import org.junit.Test;

public class IterableTest {
  @Test
  public void a() {
    int c = 1;
    for (int i : iterable(1).then(asList(2, 3, 4)).then(5))
      assertEquals(c++, i);
    c = 1;
    for (int i : combine(iterable(1), asList(2, 3, 4), iterable(5)))
      assertEquals(c++, i);
  }
}
