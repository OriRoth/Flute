package util;

import static util.Functionals.empty;
import static util.Lazy.lazy;
import static util.PredefinedInitializable.initializable;
import static util.fluent.Fluent.fluent;
import static util.fluent.Fluent.fluentFor;
import static util.fluent.FluentWrapper.fluent;
import static util.mutable.M.mutable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import util.mutable.M;
import util.mutable.MBoolean;

public class IterableWrapper<T> implements Iterable<T> {
  protected T first;
  protected Initializable<List<Iterable<T>>> posters = initializable(() -> new LinkedList<>());

  @SafeVarargs
  public static <T> IterableWrapper<T> combine(Iterable<T>... iterables) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    Collections.addAll(ret.posters.get(), iterables);
    return ret;
  }

  public static <T> IterableWrapper<T> combine(Collection<Iterable<T>> iterables) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    ret.posters.get().addAll(iterables);
    return ret;
  }

  public static <T> IterableWrapper<T> iterable(T first) {
    IterableWrapper<T> ret = new IterableWrapper<>();
    ret.first = Objects.requireNonNull(first);
    return ret;
  }

  @FluentAPI
  public IterableWrapper<T> then(Iterable<T> iterable) {
    posters.get().add(iterable);
    return this;
  }

  @FluentAPI
  public IterableWrapper<T> then(@SuppressWarnings("unchecked") Iterable<T>... iterables) {
    Collections.addAll(posters.get(), iterables);
    return this;
  }

  @FluentAPI
  public IterableWrapper<T> then(Collection<Iterable<T>> iterables) {
    posters.get().addAll(iterables);
    return this;
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
