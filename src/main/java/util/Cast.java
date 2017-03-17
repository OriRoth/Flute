package util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Cast {
  public static <T> Supplier<T> supplier(Supplier<T> supplier) {
    return supplier;
  }

  public static <T, R> Function<T, R> supplier(Function<T, R> function) {
    return function;
  }

  public static <T> Consumer<T> supplier(Consumer<T> consumer) {
    return consumer;
  }

  public static <T, R> Functional<T, R> supplier(Functional<T, R> functional) {
    return functional;
  }
}
