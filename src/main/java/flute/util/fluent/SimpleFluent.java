package flute.util.fluent;

import flute.util.FluentAPI;

@FluentAPI
public interface SimpleFluent<S> extends Fluent<S, S> {
  @SuppressWarnings("unchecked")
  @Override
  default S target() {
    return (S) this;
  }
}
