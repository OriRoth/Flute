package util.trees;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * A tree interface. In this interface, each node is a tree as well.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          tree node value type
 */
public interface Tree<T> extends List<Tree<T>> {
  public T value();

  public static <T> Tree<T> leaf(T t) {
    abstract class _Tree<X> extends LinkedList<Tree<X>> implements Tree<X> {
      private static final long serialVersionUID = 8344439290938922855L;

      @Override
      public void add(int index, Tree<X> element) {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean addAll(Collection<? extends Tree<X>> c) {
        throw new UnsupportedOperationException();
      }
    }
    return new _Tree<T>() {
      private static final long serialVersionUID = -2663113094982329257L;

      @Override
      public T value() {
        return t;
      }
    };
  }
}
