package util.trees;

import java.util.Collection;

public interface Tree<T> extends Collection<T> {
  Collection<? extends Tree<T>> children();

  Collection<? extends Tree<T>> descendants();
  
  boolean addChild(Tree<T> child);
  
  boolean removeChild(Tree<T> child);
  
  default boolean removeDescendant(Tree<T> descendant) {
    if (removeChild(descendant))
      return true;
    for (Tree<T> child : children())
      if (child.removeDescendant(descendant))
        return true;
    return false;
  }
  
  default int size() {
    int sum = 1;
    for (Tree<T> child : children())
      sum += child.size();
    return sum;
  }
  
  default int numChildren() {
    return children().size();
  }
}
