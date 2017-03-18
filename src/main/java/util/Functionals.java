package util;

import java.util.function.Consumer;

public class Functionals {
  public static <T> Consumer<T> empty() {
    return t -> {
      //
    };
  }
}
