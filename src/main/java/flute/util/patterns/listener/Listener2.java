package flute.util.patterns.listener;

import java.util.function.Function;

/**
 * A listener for the listener DP. Returns values when listening to events.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <E>
 * @param <R>
 */
@FunctionalInterface
public interface Listener2<E extends Event, R> extends Listener<E>, Function<E, R> {
  @Override
  default void accept(E event) {
    apply(event);
  }
}
