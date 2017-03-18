package util.fluent;

import util.FluentAPI;

@FluentAPI
public interface SimpleFluent<S> extends Fluent<S, S> {
  @SuppressWarnings("unchecked")
  @Override
  default S target() {
    return (S) this;
  }
}
