package util.fluent;

import util.mutable.MBoolean;

public class AbstractFluent<S> implements SimpleFluent<S> {
  private MBoolean validator = new MBoolean(true);

  @Override
  public MBoolean validator() {
    return validator;
  }

}
