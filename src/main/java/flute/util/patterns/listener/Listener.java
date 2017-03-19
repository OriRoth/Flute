package flute.util.patterns.listener;

import java.util.function.Consumer;

/**
 * A listener for the listener DP.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <E>
 *          events to listen to
 */
@FunctionalInterface
public interface Listener<E extends Event> extends Consumer<E> {
  //
}
