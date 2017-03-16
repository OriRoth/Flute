package util.patterns.visitor;

public interface Visitor1<S extends Site> extends Visitor<S> {
  @Override
  public default void accept(S site) {
    check(site);
  }

  public boolean check(S site);
}
