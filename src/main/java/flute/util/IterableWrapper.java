package flute.util;

import static flute.util.Functionals.empty;
import static flute.util.Lazy.lazy;
import static flute.util.PredefinedInitializable.initializable;
import static flute.util.fluent.Fluent.fluentFor;
import static flute.util.fluent.Fluent.fluent;
import static flute.util.fluent.FluentWrapper.fluent;
import static flute.util.mutable.M.mutable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import flute.util.mutable.M;
import flute.util.mutable.MBoolean;

/**
 * An {@link Iterable} wrapper that support various ways of concating iterables.
 * <code>
 * for (int i : iterable(1).then(intsList)) {
 *   ...
 * }
 * </code>
 *
 * <code>
 * for (String s : get(intsList).map(s -> Integer.parse(s))) {
 *   ...
 * }
 * </code>
 * 
 * And many more! All fluent API.
 * 
 * @author Ori Roth
 * @since Mar 19, 2017
 * @param <T>
 *          JD
 */
@FluentAPI
public class IterableWrapper<T> implements Iterable<T> {
  protected T first;
  protected Initializable<List<Iterable<T>>> posters = initializable(() -> new LinkedList<>());

  /**
   * Decorates an {@link Iterable} with {@link IterableWrapper}.
   * 
   * @param iterable
   *          JD
   * @return wrapped iterable
   */
  @Decorator
  public static <T> IterableWrapper<T> get(Iterable<T> iterable) {
    return nothing((T) null).then(iterable);
  }

  /**
   * An empty iterable, i.e. its {@link Iterator}'s {@link Iterator#hasNext}
   * returns false at all time. The parameter is required for casting:
   * <code>... nothing((T) null) ...</code>
   * 
   * @param __
   *          JD
   * @return empty iterable
   */
  public static <T> IterableWrapper<T> nothing(T __) {
    return new IterableWrapper<T>() {
      @Override
      public Iterator<T> iterator() {
        return new Iterator<T>() {
          @Override
          public boolean hasNext() {
            return false;
          }

          @Override
          public T next() {
            return null;
          }
        };
      }
    };
  }

  /**
   * Returns a combined iterable, whose iterator iterating through all of the
   * supplied iterables' values.
   * 
   * @param iterables
   *          JD
   * @return combined iterable
   */
  @SafeVarargs
  public static <T> IterableWrapper<T> combine(Iterable<T>... iterables) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    Collections.addAll(ret.posters.get(), iterables);
    return ret;
  }

  /**
   * Returns a combined iterable, whose iterator iterating through all of the
   * supplied iterables' values.
   * 
   * @param iterables
   *          JD
   * @return combined iterable
   */
  public static <T> IterableWrapper<T> combine(Collection<Iterable<T>> iterables) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    ret.posters.get().addAll(iterables);
    return ret;
  }

  // TODO Roth: test to see if it works
  /**
   * Returns an iterable that iterates through its value if it is an iterable,
   * recursively. Possible in cases such as:
   * <code>class A implements Iterable<A> {...}</code>
   * 
   * @param possiblyIterable
   *          a possibly recursive iterable
   * @return a recursive iterable
   */
  @SuppressWarnings("unchecked")
  public static <T> IterableWrapper<T> recursive(T possiblyIterable) {
    return iterable(possiblyIterable).then(possiblyIterable instanceof Iterable
        ? (IterableWrapper<T>) get((Iterable<?>) possiblyIterable).map(t -> recursive(t)) : nothing((T) null));
  }

  /**
   * @param first
   *          an object
   * @return iterable that contains this object only
   */
  public static <T> IterableWrapper<T> iterable(T first) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    ret.first = Objects.requireNonNull(first);
    return ret;
  }

  /**
   * @param firsts
   *          some objects
   * @return iterable that contains those object only
   */
  @SafeVarargs
  public static <T> IterableWrapper<T> iterable(T... firsts) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    ret.posters.get().add(Arrays.asList(firsts));
    return ret;
  }

  /**
   * Concates a single object to this iterable.
   * 
   * @param t
   *          an object
   * @return this iterable
   */
  @FluentAPI
  public IterableWrapper<T> then(T t) {
    posters.get().add(iterable(t));
    return this;
  }

  /**
   * Concates some objects to this iterable.
   * 
   * @param ts
   *          some objects
   * @return this iterable
   */
  @FluentAPI
  @SafeVarargs
  public final IterableWrapper<T> then(T... ts) {
    posters.get().add(Arrays.asList(ts));
    return this;
  }

  /**
   * Concat an iterable to this iterable.
   * 
   * @param iterable
   *          an iterable
   * @return this iterable
   */
  @FluentAPI
  public IterableWrapper<T> then(Iterable<T> iterable) {
    posters.get().add(iterable);
    return this;
  }

  /**
   * Concat some iterables to this iterable.
   * 
   * @param iterables
   *          some iterables
   * @return this iterable
   */
  @FluentAPI
  public IterableWrapper<T> then(@SuppressWarnings("unchecked") Iterable<T>... iterables) {
    Collections.addAll(posters.get(), iterables);
    return this;
  }

  /**
   * Concat some iterables to this iterable.
   * 
   * @param iterables
   *          some iterables
   * @return this iterable
   */
  @FluentAPI
  public IterableWrapper<T> then(Collection<Iterable<T>> iterables) {
    posters.get().addAll(iterables);
    return this;
  }

  /**
   * Converts this iterable using a mapping function.
   * 
   * @param function
   *          mapping function
   * @return this iterable
   */
  public <S> IterableWrapper<S> map(Function<T, S> function) {
    final Iterator<T> original = iterator();
    return new IterableWrapper<S>() {
      @Override
      public Iterator<S> iterator() {
        return new Iterator<S>() {
          @Override
          public boolean hasNext() {
            return original.hasNext();
          }

          @Override
          public S next() {
            return function.apply(original.next());
          }
        };
      }
    };
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      MBoolean onFirst = new MBoolean(first != null);
      Supplier<Iterator<Iterable<T>>> iterables = lazy(() -> posters.get().iterator());
      M<Iterator<T>> currentIterator = mutable();

      @Override
      public boolean hasNext() {
        return onFirst.booleanValue() || posters.initialized()
            && fluentFor(currentIterator, i -> (i == null || !i.hasNext()) && iterables.get().hasNext(),
                __ -> iterables.get().next().iterator(), empty()).get().hasNext();
      }

      @Override
      public T next() {
        return fluent(onFirst.get(), __ -> onFirst.set(false)).booleanValue() ? first
            : fluent(currentIterator).validate(i -> i == null || !i.get().hasNext())
                .d0(i -> i.set(iterables.get().next().iterator())).origin().get().next();
      }
    };
  }
}
