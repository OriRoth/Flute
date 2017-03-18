package util.fluent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A fluent wrapper. Allows fluent programming with any object. Client may
 * extends this class in order to create fluent wrappers for existing objects.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class Fluent<O> {
  private final O inner;
  private boolean invalid;

  public Fluent(O object) {
    inner = object;
  }

  /**
   * Wraps an object with a fluent wrapper. Then clients may use {@link #d0},
   * {@link #validate} and {@link #elze} as a fluent programming, then returning
   * original object using {@link #origin}.
   * 
   * @param object
   *          object to wrap
   * @return fluent wrapper for the object
   */
  public static <O> Fluent<O> fluent(O object) {
    return new Fluent<>(object);
  }

  /**
   * A single fluent action activation. Equivalent to
   * <code>fluent(object).d0(action).origin()</code>.
   * 
   * @param object
   *          original object
   * @param action
   *          action to perform on the object
   * @return original object
   */
  public static <O> O fluent(O object, Consumer<O> action) {
    action.accept(object);
    return object;
  }

  /**
   * A fluent initiation of conditional actions stream. Returns validated
   * {@link Fluent} iff condition is true. Original object (returned from
   * {@link #origin}) is null.
   * 
   * @param condition
   *          validates the fluent stream
   * @return fluent object
   */
  public static Fluent<Void> fluentIf(boolean condition) {
    return condition ? fluent(null) : silent(null);
  }

  /**
   * @param object
   *          a source object for the {@link Fluent}
   * @return an invalidated {@link Fluent} for the object
   */
  public static <O> Fluent<O> silent(O object) {
    Fluent<O> ret = new Fluent<O>(object);
    ret.invalid = true;
    return ret;
  }

  /**
   * Does an action on the original object, only if the {@link Fluent} is
   * validated- otherwise does nothing.
   * 
   * @param action
   *          action to perform on wrapped object
   * @return this fluent
   */
  public Fluent<O> d0(Consumer<O> action) {
    if (!invalid)
      action.accept(inner);
    return this;
  }

  /**
   * Converts this fluent into a fluent of another object. The new fluent is
   * valid iff the current one is.
   * 
   * @param action
   *          a function that gets the original object and returns the object
   *          for the new fluent
   * @return a new fluent for the action's output
   */
  public <T> Fluent<T> lane(Function<O, T> action) {
    if (!invalid)
      return new Fluent<T>(action.apply(inner));
    Fluent<T> ret = new Fluent<T>(null);
    ret.invalid = true;
    return ret;
  }

  /**
   * Validates this fluent with a predicate. An invalid fluent will not perform
   * actions on the wrapped object, yet return it as usual on {@link #origin}.
   * 
   * @param predicate
   *          a predicate to validate
   * @return this fluent
   * @see #elze
   */
  public Fluent<O> validate(Predicate<O> predicate) {
    if (!invalid && !predicate.test(inner))
      invalid = true;
    return this;
  }

  /**
   * Toggle the validation state of this fluent.
   * 
   * @return this fluent
   */
  public Fluent<O> elze() {
    invalid = !invalid;
    return this;
  }

  /**
   * @return the original wrapped object.
   */
  public O origin() {
    return inner;
  }
}
