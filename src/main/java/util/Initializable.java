package util;

import java.util.Objects;
import java.util.function.Supplier;

import static util.Test.*;

/**
 * A lazy wrapper for object.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class Initializable<T> {
  private T value;
  private boolean initialized;

  /**
   * @return an empty initializable
   * @see #suggest
   * @see #get
   */
  public static <T> Initializable<T> initializable() {
    return new Initializable<>();
  }

  /**
   * @param t
   *          initial object
   * @return initialized initializable
   */
  public static <T> Initializable<T> initializable(T t) {
    return new Initializable<T>().set(t);
  }

  /**
   * Set the wrapped object in this initializable. Will work only if this
   * initializable is not yet initialized.
   * 
   * @param value
   *          initial value for this initializable
   * @return this initializable
   * @throws IllegalStateException
   *           if this initializable is already initialized
   */
  protected Initializable<T> set(T value) {
    if (initialized)
      throw new IllegalStateException();
    initialized = true;
    this.value = value;
    return this;
  }

  /**
   * @return wrapped object or null if this initializable is not yet initialized
   */
  public T get() {
    return value;
  }

  /**
   * @return true iff this initializable is initialized
   */
  public boolean initialized() {
    return initialized;
  }

  /**
   * Suggests an initialization for this initializable. If the initializable is
   * already initialized, nothing happens.
   * 
   * @param supplier
   *          supplier of initial object for this initializable
   * @return the wrapped object in this initializable
   */
  public T suggest(Supplier<T> supplier) {
    return initialized() ? get() : set(supplier.get()).get();
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Initializable ? equalsNullable(value, ((Initializable<?>) obj).value)
        : equalsNullable(value, obj);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  protected Object clone() {
    return initializable(value);
  }
}
