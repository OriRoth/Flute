package flute.util;

import static flute.util.Test.*;

import java.util.Objects;
import java.util.function.Supplier;

import flute.util.mutable.M;

/**
 * A lazy wrapper for some object. Used for fluent lazy varying initialization:
 * <code>
 * class GUI {
 *   Picture logo;
 *   ...
 *   if (logo == null)
 *     logo = loadPicture("logo1");
 *   logo. ...
 *   ...
 *   if (logo == null)
 *     logo = loadPicture("logo2");
 *   logo. ...
 *   ...
 *   assert logo != null;
 *   logo.show();
 *   ...
 * }
 * </code> ==> <code>
 * class GUI {
 *   Initializable<Picture> logo = initializable();
 *   ...
 *   logo.suggest(() -> loadPicture("logo1")). ...
 *   ...
 *   logo.suggest(() -> loadPicture("logo2")). ...
 *   ...
 *   logo.show();
 *   ...
 * }
 * </code>
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @see Lazy#lazy(Supplier)
 */
public class Initializable<T> extends M<T> {
  protected boolean initialized;

  protected Initializable(T value) {
    super(value);
  }

  /**
   * @return an empty initializable
   * @see #suggest
   * @see #get
   */
  public static <T> Initializable<T> initializable() {
    return new Initializable<>(null);
  }

  /**
   * @param t
   *          initial object
   * @return initialized initializable
   */
  public static <T> Initializable<T> initializable(T t) {
    return new Initializable<T>(t);
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
  protected Initializable<T> setValue(T value) {
    if (initialized())
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
    return initialized() ? get() : setValue(supplier.get()).get();
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
