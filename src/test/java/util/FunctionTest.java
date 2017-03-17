package util;

import org.junit.Test;

public class FunctionTest {
  @Test
  public void compilation() {
    Functional<Integer, Integer> f = x -> x + 1;
    f.accept(1);
    f.apply(1);
  }
}
