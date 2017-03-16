package util.mutable;

import static util.Test.*;

import java.util.Objects;

public class M<T> {
  protected T value;

  public M(T value) {
    this.value = value;
  }

  public static <T> M<T> mutable(T value) {
    return new M<>(value);
  }

  public T get() {
    return value;
  }

  public M<T> set(T value) {
    this.value = value;
    return this;
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