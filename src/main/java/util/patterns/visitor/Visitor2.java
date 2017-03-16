package util.patterns.visitor;

import java.util.function.Function;

@FunctionalInterface
public interface Visitor2<S extends Site, R> extends Visitor<S>, Function<S, R> {
  @Override
  default void accept(S site) {
    apply(site);
  }
}
