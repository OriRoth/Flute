package util.mutable;

import static util.Test.*;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Mutable wrapper for objects.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <T>
 *          JD
 */
public class M<T> implements Supplier<T> {
  protected T value;

  /**
   * Create a mutable wrapper, initialized with an object.
   * 
   * @param value
   *          initialization value
   */
  public M(T value) {
    this.value = value;
  }

  /**
   * Create a mutable wrapper, initialized with null.
   * 
   * @return mutable initialized with null
   */
  public static <T> M<T> mutable() {
    return new M<>(null);
  }

  /**
   * Create a mutable wrapper, initialized with an object.
   * 
   * @param value
   *          initialization value
   * @return mutable initialized with this value
   */
  public static <T> M<T> mutable(T value) {
    return new M<>(value);
  }

  /**
   * @return inner value
   */
  @Override
  public T get() {
    return value;
  }

  /**
   * Set inner value.
   * 
   * @param value
   *          replacement value
   */
  public void set(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof M ? equalsNullable(value, ((M<?>) obj).value) : equalsNullable(value, obj);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  protected Object clone() {
    return new M<>(value);
  }
}
