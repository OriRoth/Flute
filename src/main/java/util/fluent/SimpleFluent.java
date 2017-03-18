package util.fluent;

public interface SimpleFluent<S> extends Fluent<S, S> {
  @SuppressWarnings("unchecked")
  @Override
  default S target() {
    return (S) this;
  }
}
