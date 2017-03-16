package util.patterns.listener;

import java.util.function.Function;

public interface Listener1<E extends Event, R> extends Listener<E>, Function<E, R> {
  @Override
  default void accept(E event) {
    apply(event);
  }
}
