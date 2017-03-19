package util;

import java.util.function.Consumer;

/**
 * Functionals utilities.
 * 
 * @author Ori Roth
 * @since Mar 19, 2017
 */
public class Functionals {
  /**
   * A passive consumer, i.e. does nothing on input.
   * 
   * @return passive consumer
   */
  public static <T> Consumer<T> empty() {
    return t -> {
      //
    };
  }
}
