package flute.util.trees;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// TODO Roth: add default algorithms
/**
 * A tree interface. In this interface, each node is a tree as well.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          tree node value type
 */
public interface Tree<T> extends List<Tree<T>> {
  /**
   * @return node value
   */
  public T value();

  /**
   * @param t
   *          node value
   * @return a leaf, i.e a tree inintialized with input value that cannot be
   *         added children
   */
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

      @Override
      public boolean add(Tree<X> e) {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean addAll(int index, Collection<? extends Tree<X>> c) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void addFirst(Tree<X> e) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void addLast(Tree<X> e) {
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
