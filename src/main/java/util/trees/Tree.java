package util.trees;

import static util.IterableWrapper.*;
import static util.Test.*;
import static java.util.stream.Collectors.toList;
import static util.fluent.collections.FluentList.fluent;

import java.util.Collection;
import java.util.Iterator;

//TODO Roth: complete
/**
 * A tree interface. In this interface, each node is a tree as well.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          tree node value type
 */
public interface Tree<T> extends Collection<T> {
  public static <T> Tree<T> leaf(T t) {
//    return new Tree<T>() {
//    };
    return null;
  }

  /**
   * @return inner node value
   */
  T get();

  /**
   * @return direct children of this tree
   */
  Collection<Tree<T>> children();

  /**
   * Adds a direct child to this tree.
   * 
   * @param child
   *          child to add
   * @return true iff the child has been added successfully
   */
  boolean addChild(Tree<T> child);

  /**
   * Removes a direct child from this tree.
   * 
   * @param child
   *          child to remove
   * @return true iff the child has been removed successfully
   */
  boolean removeChild(Tree<T> child);

  /**
   * Removes a direct child from this tree.
   * 
   * @param value
   *          value of the child to remove
   * @return true iff the child has been removed successfully
   */
  default boolean removeValue(T value) {
    for (Tree<T> c : children())
      if (equalsNullable(value, c.get()))
        return removeChild(c);
    return false;
  }

  /**
   * @return descendants of this tree
   */
  default Collection<Tree<T>> descendants() {
    Collection<Tree<T>> ret = children();
    for (Tree<T> c : children())
      ret.addAll(c.descendants());
    return ret;
  }

  /**
   * Removes descendant from this tree.
   * 
   * @param descendant
   *          descendant to remove
   * @return true iff the descendant has been successfully removed
   */
  default boolean removeDescendant(Tree<T> descendant) {
    if (removeChild(descendant))
      return true;
    for (Tree<T> child : children())
      if (child.removeDescendant(descendant))
        return true;
    return false;
  }

  /**
   * Removes descendant from this tree.
   * 
   * @param value
   *          value of descendant to remove
   * @return true iff the descendant has been successfully removed
   */
  default boolean removeDescendantValue(T value) {
    if (removeValue(value))
      return true;
    for (Tree<T> child : children())
      if (child.removeDescendantValue(value))
        return true;
    return false;
  }

  /**
   * @return size of the tree
   */
  default int size() {
    int sum = 1;
    for (Tree<T> child : children())
      sum += child.size();
    return sum;
  }

  /**
   * @return number of direct children of this tree
   */
  default int numChildren() {
    return children().size();
  }

  @Override
  default boolean isEmpty() {
    return children().isEmpty();
  }

  @Override
  default boolean contains(Object o) {
    return equalsNullable(o, get()) || descendants().stream().map(t -> t.get()).collect(toList()).contains(o);
  }

  @Override
  default Iterator<T> iterator() {
    return iterable(get()).then(descendants().stream().map(t -> t.get()).collect(toList())).iterator();
  }

  @Override
  default Object[] toArray() {
    return fluent(descendants().stream().map(t -> t.get()).collect(toList())).add(0, get()).toArray();
  }

  @Override
  default <X> X[] toArray(X[] a) {
    return fluent(descendants().stream().map(t -> t.get()).collect(toList())).add(0, get()).toArray(a);
  }

  @Override
  default boolean add(T e) {
    return addChild(leaf(e));
  }

  @SuppressWarnings("unchecked")
  @Override
  default boolean remove(Object o) {
    return removeDescendantValue((T) o);
  }
  
  @Override
  default boolean containsAll(Collection<?> c) {
    return fluent(descendants().stream().map(t -> t.get()).collect(toList())).add(get()).containsAll(c);
  }
}
