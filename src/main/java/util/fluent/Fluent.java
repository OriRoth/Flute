package util.fluent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import util.mutable.MBoolean;

@SuppressWarnings("unchecked")
public interface Fluent<S> {
  /**
   * Does an action on the original object, only if the {@link FluentWrapper} is
   * validated- otherwise does nothing.
   * 
   * @param action
   *          action to perform on wrapped object
   * @return this fluent
   */
  public default S d0(Consumer<S> action) {
    if (validator().get())
      action.accept((S) this);
    return (S) this;
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
  public default <T> T lane(Function<S, T> action) {
    return action.apply((S) this);
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
  public default S validate(Predicate<S> predicate) {
    if (validator().get() && !predicate.test((S) this))
      validator().set(false);
    return (S) this;
  }

  /**
   * Toggle the validation state of this fluent.
   * 
   * @return this fluent
   */
  public default S elze() {
    validator().toggle();
    return (S) this;
  }

  /**
   * A validator for this fluent. A non-validated fluent would not take any
   * actions.
   * 
   * @return validator for this fluent
   * @see d0
   * @see validate
   * @see elze
   */
  public MBoolean validator();
}
