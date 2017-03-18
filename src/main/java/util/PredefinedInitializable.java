package util;

import java.util.function.Supplier;

public class PredefinedInitializable<T> extends Initializable<T> {
  private Supplier<T> initializationMethod;

  protected PredefinedInitializable(T value) {
    super(value);
  }

  protected PredefinedInitializable(Supplier<T> initializationMethod) {
    super(null);
    this.initializationMethod = initializationMethod;
  }

  public static <T> PredefinedInitializable<T> initializable(Supplier<T> initializationMethod) {
    return new PredefinedInitializable<>(initializationMethod);
  }

  @Override
  public T get() {
    if (!initialized) {
      initialized = true;
      set(initializationMethod.get());
    }
    return super.get();
  }
}
