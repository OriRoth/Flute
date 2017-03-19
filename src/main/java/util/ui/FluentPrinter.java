package util.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import util.FluentAPI;
import util.fluent.FluentWrapper;

/**
 * Support fluent printing operations.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          inner type
 * @see FluentWrapper
 */
@FluentAPI
public class FluentPrinter<T> extends FluentWrapper<T> {
  private final Consumer<String> printer;

  /**
   * @param object
   *          object to wrap
   */
  public FluentPrinter(T object) {
    super(object);
    printer = t -> System.out.println(t);
  }

  /**
   * @param object
   *          object to wrap
   * @param printer
   *          print operation
   */
  public FluentPrinter(T object, Consumer<String> printer) {
    super(object);
    this.printer = printer;
  }

  /**
   * @param t
   *          object to wrap
   * @return a fluent printer initialized with the input pbject
   */
  @FluentAPI
  public static <T> FluentPrinter<T> printer(T t) {
    return new FluentPrinter<T>(t);
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static <T> T print(T t) {
    System.out.println(t);
    return t;
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static int print(int t) {
    System.out.println(t);
    return t;
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static double print(double t) {
    System.out.println(t);
    return t;
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static byte print(byte t) {
    System.out.println(t);
    return t;
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static char print(char t) {
    System.out.println(t);
    return t;
  }

  /**
   * Fluent printing of an object.
   * 
   * @param t
   *          object to print
   * @return input object
   */
  @FluentAPI
  public static short print(short t) {
    System.out.println(t);
    return t;
  }

  /**
   * Prints the wrapped object, if this fluent is validated.
   * 
   * @return this fluent
   */
  @FluentAPI
  public FluentPrinter<T> print() {
    return d0(t -> printer.accept(Objects.toString(t)));
  }

  /**
   * Prints the wrapped object, if this fluent is validated.
   * 
   * @param stringer
   *          converter of wrapped object to string
   * @return this fluent
   */
  @FluentAPI
  public FluentPrinter<T> print(Function<T, String> stringer) {
    return d0(t -> printer.accept(stringer.apply(t)));
  }

  /**
   * Prints something.
   * 
   * @param string
   *          a message to print
   * @return this fluent
   */
  @FluentAPI
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
