package flute.util.fluent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import flute.util.FluentAPI;
import flute.util.mutable.M;
import flute.util.mutable.MBoolean;

@SuppressWarnings("unchecked")
@FluentAPI
public interface Fluent<S, T> {
  @FluentAPI
  public static <O> O fluent(O object, Consumer<O> action) {
    action.accept(object);
    return object;
  }

  @FluentAPI
  public static <O> M<O> fluentFor(M<O> container, Predicate<O> condition, Function<O, O> next, Consumer<O> action) {
    for (; condition.test(container.get()); container.set(next.apply(container.get())))
      action.accept(container.get());
    return container;
  }

  @FluentAPI
  public default S d0(Consumer<T> action) {
    if (validator().get())
      action.accept(target());
    return (S) this;
  }

  @FluentAPI
  public default S whileD0(Predicate<T> condition, Consumer<T> action) {
    if (validator().get())
      while (condition.test(target()))
        action.accept(target());
    return (S) this;
  }

  @FluentAPI
  public default S d0While(Consumer<T> action, Predicate<T> condition) {
    if (validator().get())
      do
        action.accept(target());
      while (condition.test(target()));
    return (S) this;
  }

  @FluentAPI
  public default <W> W lane(Function<T, W> action) {
    return action.apply(target());
  }

  @FluentAPI
  public default T laneWhile(Function<T, T> action, Predicate<T> condition) {
    T ret = target();
    do
      ret = action.apply(ret);
    while (condition.test(ret));
    return ret;
  }

  @FluentAPI
  public default T whileLane(Predicate<T> condition, Function<T, T> action) {
    T ret = target();
    while (condition.test(ret))
      ret = action.apply(ret);
    return ret;
  }

  @FluentAPI
  public default S validate(Predicate<T> predicate) {
    if (validator().get() && !predicate.test(target()))
      validator().set(false);
    return (S) this;
  }

  @FluentAPI
  public default S elze() {
    validator().toggle();
    return (S) this;
  }

  public MBoolean validator();

  public T target();
}
