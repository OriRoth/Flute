package util;

import static flute.util.IterableWrapper.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IterableTest {
  @Test
  public void compilation() {
    int c = 1;
    for (int i : iterable(1).then(asList(2, 3, 4)).then(5))
      assertEquals(c++, i);
    c = 1;
    for (int i : combine(iterable(1), asList(2, 3, 4), iterable(5)))
      assertEquals(c++, i);
    c = 1;
    for (int i : iterable(1, 2, 3, 4).then(5).then(nothing(0)))
      assertEquals(c++, i);
  }

  @Test
  public void compilation2() {
    int c = "1".hashCode();
    for (int i : nothing(0).then(1, 2).then(nothing(0)).then(3).map(i -> i + "").map(s -> s.hashCode()))
      assertEquals(c++, i);
  }
}
