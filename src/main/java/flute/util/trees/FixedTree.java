package flute.util.trees;

import java.util.ArrayList;
import java.util.Collection;

import flute.util.FluentAPI;

/**
 * Fixed size tree, i.e. there is a limited number of children allowed in this
 * tree at all time.
 * 
 * @author Ori Roth
 * @since Mar 19, 2017
 * @param <T>
 *          JD
 */
public class FixedTree<T> extends ArrayList<Tree<T>> implements Tree<T> {
  private static final long serialVersionUID = -5603703432180427358L;
  private T value;
  private int limit;

  /**
   * @param value
   *          node value
   * @param limit
   *          children limit
   */
  public FixedTree(T value, int limit) {
    this.value = value;
    this.limit = limit;
  }

  @Override
  public void add(int index, Tree<T> element) {
    if (size() > limit - 1)
      throw new IllegalStateException();
    super.add(index, element);
  }

  @Override
  public boolean add(Tree<T> e) {
    if (size() > limit - 1)
      throw new IllegalStateException();
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends Tree<T>> c) {
    if (c != null && size() > limit - c.size())
      throw new IllegalStateException();
    return super.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends Tree<T>> c) {
    if (c != null && size() > limit - c.size())
      throw new IllegalStateException();
    return super.addAll(index, c);
  }

  @Override
  public T value() {
    return value;
  }

  /**
   * @return children limit of this tree
   */
  public int limit() {
    return limit;
  }

  /**
   * @param limit
   *          new children limit
   * @return this tree
   */
  @FluentAPI
  public FixedTree<T> limit(int limit) {
    this.limit = limit;
    return this;
  }
}
