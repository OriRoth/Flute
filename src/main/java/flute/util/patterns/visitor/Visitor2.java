package flute.util.patterns.visitor;

import java.util.function.Function;

/**
 * A visitor for the visitor DP. Returns result when visiting site.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <S>
 *          sites to visit
 * @param <R>
 *          values to return
 */
@FunctionalInterface
public interface Visitor2<S extends Site, R> extends Visitor<S>, Function<S, R> {
  @Override
  default void accept(S site) {
    apply(site);
  }
}
