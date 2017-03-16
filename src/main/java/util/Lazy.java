package util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import util.collections.Seen;

import static util.Initializable.*;
import static util.Fluent.*;

public class Lazy {
  public static <T> Supplier<T> lazy(Supplier<T> supplier) {
    return new Supplier<T>() {
      Initializable<T> value = initializable();

      public T get() {
        return value.suggest(supplier.get());
      }
    };
  }

  public static <T> Consumer<T> lazy(Consumer<T> consumer) {
    return new Consumer<T>() {
      Seen<T> seen = new Seen<>();

      @Override
      public void accept(T t) {
        seen.ifNotSeen(t, _t -> consumer.accept(_t));
      }
    };
  }

  public static <T, R> Function<T, R> lazy(Function<T, R> function) {
    return new Function<T, R>() {
      Map<T, R> results = new HashMap<>();

      @Override
      public R apply(T t) {
        return fluent(results).validate(m -> !m.containsKey(t)) //
            .d0(m -> m.put(t, function.apply(t))).halt().get(t);
      }
    };
  }
}
