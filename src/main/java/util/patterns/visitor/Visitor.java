package util.patterns.visitor;

import java.util.function.Consumer;

@FunctionalInterface
public interface Visitor<S extends Site> extends Consumer<S> {
  //
}
