package util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Fluent<O> {
  private final O inner;

  public Fluent(O object) {
    inner = object;
  }

  public static <O> Fluent<O> fluent(O object) {
    return new Fluent<>(object);
  }

  public Fluent<O> d0(Consumer<O> action) {
    action.accept(inner);
    return this;
  }

  public <T> Fluent<T> lane(Function<O, T> action) {
    return new Fluent<T>(action.apply(inner));
  }

  @SuppressWarnings("unchecked")
  public Fluent<O> validate(Predicate<O> predicate) {
    return predicate.test(inner) ? this : (Fluent<O>) EMPTY;
  }

  public O halt() {
    return inner;
  }

  // TODO Roth: consider replacing with dynamic empty()
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private static final Fluent<?> EMPTY = new Fluent(null) {
    @Override
    public Fluent<?> d0(Consumer action) {
      return this;
    }

    @Override
    public Fluent<?> lane(Function action) {
      return this;
    }

    @Override
    public Fluent<?> validate(Predicate predicate) {
      return this;
    }
  };
}
