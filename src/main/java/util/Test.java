package util;

import java.util.Collection;

/**
 * Boolean evaluation utility.
 * 
 * @author Ori Roth
 * @since Mar 19, 2017
 */
public class Test {
  /**
   * @param o1
   *          a nullable Object
   * @param o2
   *          a nullable Object
   * @return true iff they are both null or equal
   */
  public static boolean equalsNullable(Object o1, Object o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  /**
   * @param c1
   *          a collection
   * @param c2
   *          a collection
   * @return true iff both collections are null or contain each other
   */
  public static boolean collectionsEquals(Collection<?> c1, Collection<?> c2) {
    return c1 == null ? c2 == null : c2 == null ? false : c1.containsAll(c2) && c2.containsAll(c1);
  }
}
