package util;

import java.util.Objects;
import java.util.function.Supplier;

import static util.Test.*;

public class Initializable<T> {
  private T value;
  private boolean initialized;

  public static <T> Initializable<T> initializable() {
    return new Initializable<>();
  }

  public static <T> Initializable<T> initializable(T t) {
    return new Initializable<T>().set(t);
  }

  public Initializable<T> set(T value) {
    if (initialized)
      throw new IllegalStateException();
    initialized = true;
    this.value = value;
    return this;
  }

  public T get() {
    return value;
  }

  public boolean initialized() {
    return initialized;
  }

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
