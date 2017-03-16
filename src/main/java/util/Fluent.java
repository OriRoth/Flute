package util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fluent<O> {
  private final O inner;
  private boolean invalid;

  public Fluent(O object) {
    inner = object;
  }

  public static <O> Fluent<O> fluent(O object) {
    return new Fluent<>(object);
  }
  
  public static <O> Fluent<O> silent(O object) {
    Fluent<O> ret = new Fluent<O>(object);
    ret.invalid = true;
    return ret;
  }

  public Fluent<O> d0(Consumer<O> action) {
    if (!invalid)
      action.accept(inner);
    return this;
  }

  public <T> Fluent<T> lane(Function<O, T> action) {
    if (!invalid)
      return new Fluent<T>(action.apply(inner));
    Fluent<T> ret = new Fluent<T>(null);
    ret.invalid = true;
    return ret;
  }

  public Fluent<O> validate(Predicate<O> predicate) {
    if (!invalid && !predicate.test(inner))
      invalid = true;
    return this;
  }

  public O origin() {
    return inner;
  }
}
