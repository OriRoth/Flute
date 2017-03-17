package util;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface Functional<T, R> extends java.util.function.Function<T, R>, Consumer<T> {
  @Override
  default void accept(T t) {
    apply(t);
  }

  @Override
  default <V> Functional<T, V> andThen(java.util.function.Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return t -> after.apply(apply(t));
  }

  @Override
  default <V> Functional<V, R> compose(java.util.function.Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return t -> apply(before.apply(t));
  }

  @Override
  default Consumer<T> andThen(Consumer<? super T> after) {
    throw new UnsupportedOperationException();
  }
}
