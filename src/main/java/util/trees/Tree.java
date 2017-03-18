package util.trees;

import java.util.Collection;

/**
 * A tree interface. In this interface, each node is a tree as well.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          tree node value type
 */
public interface Tree<T> extends Collection<T> {
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
}
