package util.fluent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import util.mutable.MBoolean;

@SuppressWarnings("unchecked")
public interface Fluent<S, T> {
  public default S d0(Consumer<T> action) {
    if (validator().get())
      action.accept(target());
    return (S) this;
  }

  public default <W> W lane(Function<T, W> action) {
    return action.apply(target());
  }

  public default S validate(Predicate<T> predicate) {
    if (validator().get() && !predicate.test(target()))
      validator().accept(false);
    return (S) this;
  }

  public default S elze() {
    validator().toggle();
    return (S) this;
  }

  public MBoolean validator();

  public T target();
}
