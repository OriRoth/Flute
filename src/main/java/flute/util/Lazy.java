package flute.util;

import static flute.util.Initializable.*;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import flute.util.collections.Seen;
import flute.util.fluent.collections.FluentMap;

/**
 * Lazy wrappers for functional interfaces.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class Lazy {
  /**
   * A lazy wrapper for a {@link Supplier}. This {@link Supplier} would record
   * the first value the input supplier had supplied, then return it every time
   * on {@link Supplier#get}. The value is calculated on the first
   * {@link Supplier#get}.
   * 
   * @param supplier
   *          a supplier to wrap
   * @return a lazy version of this supplier
   */
  @Decorator
  public static <T> Supplier<T> lazy(Supplier<T> supplier) {
    Objects.requireNonNull(supplier);
    return new Supplier<T>() {
      Initializable<T> value = initializable();

      public T get() {
        return value.suggest(supplier);
      }
    };
  }

  /**
   * A lazy wrapper for a {@link Consumer}. If it {@link Consumer#accept}s an
   * object it already consumed, it will do nothing- else activating original
   * consumer.
   * 
   * @param consumer
   *          a consumer to wrap
   * @return a lazy version of this consumer
   */
  @Decorator
  public static <T> Consumer<T> lazy(Consumer<T> consumer) {
    Objects.requireNonNull(consumer);
    return new Consumer<T>() {
      Seen<T> seen = new Seen<>();

      @Override
      public void accept(T t) {
        seen.ifNotSeen(t, _t -> consumer.accept(_t));
      }
    };
  }

  /**
   * A lazy wrapper for a {@link Function}. If it gets an input it already
   * calculated, it would return the previous calculation.
   * 
   * @param function
   *          a function to wrap
   * @return a lazy version of this function
   */
  @Decorator
  public static <T, R> Function<T, R> lazy(Function<T, R> function) {
    Objects.requireNonNull(function);
    return new Function<T, R>() {
      FluentMap<T, R> results = new FluentMap<>();

      @Override
      public R apply(T t) {
        return results.ifNotContainsKey(t).put(t, function.apply(t)).get(t);
      }
    };
  }
}
