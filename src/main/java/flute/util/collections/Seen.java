package flute.util.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A {@link Set} used for documenting touched objects.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          JD
 */
public class Seen<T> extends HashSet<T> {
  private static final long serialVersionUID = -1578854262738867470L;

  /**
   * Checks if object has been seen. Anyway the input object is now seen.
   * 
   * @param t
   *          object to check
   * @return true iff it has already been seen
   */
  public boolean seen(T t) {
    if (contains(t))
      return true;
    add(t);
    return false;
  }

  /**
   * Checks if objects have been seen. Anyway the input objects are now seen.
   * 
   * @param collection
   *          objects to check
   * @return true iff all the objects have already been seen
   */
  public boolean seenAll(Collection<T> collection) {
    if (containsAll(collection))
      return true;
    addAll(collection);
    return false;
  }

  /**
   * @param t
   *          object to check
   * @param operation
   *          done iff input object has not been seen
   */
  public void ifSeen(T t, Consumer<T> operation) {
    if (seen(t))
      operation.accept(t);
  }

  /**
   * @param t
   *          object to check
   * @param operation
   *          done iff input object has been seen
   */
  public void ifNotSeen(T t, Consumer<T> operation) {
    if (!seen(t))
      operation.accept(t);
  }

  /**
   * @param collection
   *          objects to check
   * @param operation
   *          done iff input object has not been seen
   */
  public void ifSeenAll(Collection<T> collection, Consumer<T> operation) {
    if (seenAll(collection))
      collection.forEach(operation);
  }

  /**
   * @param collection
   *          objects to check
   * @param operation
   *          done iff input object has been seen
   */
  public void ifNotSeenAll(Collection<T> collection, Consumer<T> operation) {
    if (!seenAll(collection))
      collection.forEach(operation);
  }
}
