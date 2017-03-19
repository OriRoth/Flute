package util.fluent;

import util.FluentAPI;
import util.mutable.MBoolean;

@FluentAPI
public class AbstractFluent<S> implements SimpleFluent<S> {
  private MBoolean validator = new MBoolean(true);

  @Override
  public MBoolean validator() {
    return validator;
  }
}
