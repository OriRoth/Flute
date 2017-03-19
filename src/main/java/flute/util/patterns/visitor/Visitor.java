package flute.util.patterns.visitor;

import java.util.function.Consumer;

/**
 * A visitor for the visitor DP.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 * @param <S>
 *          sites to visit
 */
@FunctionalInterface
public interface Visitor<S extends Site> extends Consumer<S> {
  //
}
