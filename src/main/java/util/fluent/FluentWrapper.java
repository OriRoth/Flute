package util.fluent;

import java.util.function.Consumer;

import util.FluentAPI;
import util.mutable.MBoolean;

@FluentAPI
public class FluentWrapper<O> implements Fluent<FluentWrapper<O>, O> {
  private final O inner;
  private MBoolean validator = new MBoolean(true);

  public FluentWrapper(O object) {
    inner = object;
  }

  public static <O> FluentWrapper<O> fluent(O object) {
    return new FluentWrapper<>(object);
  }

  @FluentAPI
  public static <O> O fluent(O object, Consumer<O> action) {
    action.accept(object);
    return object;
  }

  @FluentAPI
  public static FluentWrapper<Void> fluentIf(boolean condition) {
    return condition ? fluent(null) : silent(null);
  }

  public static <O> FluentWrapper<O> silent(O object) {
    FluentWrapper<O> ret = new FluentWrapper<O>(object);
    ret.validator.set(false);
    return ret;
  }

  public O origin() {
    return inner;
  }

  @Override
  public MBoolean validator() {
    return validator;
  }

  @Override
  public O target() {
    return inner;
  }
}
