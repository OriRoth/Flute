package util.mutable;

public class MBoolean extends M<Boolean> {

  public MBoolean(Boolean value) {
    super(value);
  }

  public boolean booleanValue() {
    return value;
  }

  public MBoolean toggle() {
    accept(!get());
    return this;
  }
}
