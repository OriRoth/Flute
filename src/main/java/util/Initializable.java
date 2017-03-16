package util;

public class Initializable<T> {
  private T value;
  private boolean initialized;

  public static <T> Initializable<T> initializable() {
    return new Initializable<>();
  }

  public static <T> Initializable<T> initializable(T t) {
    return new Initializable<T>().set(t);
  }

  public Initializable<T> set(T value) throws IllegalStateException {
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

  public T suggest(T value) {
    return initialized() ? get() : set(value).get();
  }
}
