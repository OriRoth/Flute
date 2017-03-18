package util.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import util.fluent.Fluent;

public class FluentPrinter<T> extends Fluent<T> {
  private final Consumer<String> printer;

  public FluentPrinter(T object) {
    super(object);
    printer = t -> System.out.println(t);
  }

  public FluentPrinter(T object, Consumer<String> printer) {
    super(object);
    this.printer = printer;
  }

  public static <T> FluentPrinter<T> printer(T t) {
    return new FluentPrinter<T>(t);
  }

  public static <T> T print(T t) {
    System.out.println(t);
    return t;
  }

  public FluentPrinter<T> print() {
    return d0(t -> printer.accept(Objects.toString(t)));
  }

  public FluentPrinter<T> print(Function<T, String> stringer) {
    return d0(t -> printer.accept(stringer.apply(t)));
  }

  public FluentPrinter<T> print(String string) {
    return d0(t -> printer.accept(string));
  }

  @Override
  public FluentPrinter<T> d0(Consumer<T> action) {
    return (FluentPrinter<T>) super.d0(action);
  }

  @Override
  public FluentPrinter<T> elze() {
    return (FluentPrinter<T>) super.elze();
  }

  @Override
  public FluentPrinter<T> validate(Predicate<T> predicate) {
    return (FluentPrinter<T>) super.validate(predicate);
  }
}
