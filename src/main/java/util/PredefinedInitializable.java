package util;

import java.util.function.Supplier;

/**
 * A lazy wrapper for some object. Usage example: <code>
 * class GUI {
 *   Picture logo = loadPicture("logo");
 *   ...
 *   logo.show()
 *   ...
 * }
 * </code> ==> <code>
 * class GUI {
 *   Picture logo;
 *   ...
 *   if (logo == null)
 *     logo = loadPicture("logo");
 *   logo.show()
 *   ...
 * }
 * </code> ==> <code>
 * class GUI {
 *   Initializable<Picture> logo = initializable(() -> loadPicture("logo"));
 *   ...
 *   logo.show();
 *   ...
 * }
 * </code>
 * 
 * @author Ori Roth
 * @since Mar 19, 2017
 * @param <T>
 */
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
