package util;

import java.util.Collection;

public class Test {
  public static boolean equalsNullable(Object o1, Object o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  public static boolean equalsIgnoreOrder(Collection<?> c1, Collection<?> c2) {
    return c1 == null ? c2 == null : c2 == null ? false : c1.containsAll(c2) && c2.containsAll(c1);
  }
}
