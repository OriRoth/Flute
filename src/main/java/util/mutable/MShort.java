package util.mutable;

public class MShort extends M<Short> {
  public MShort(Short value) {
    super(value);
  }

  public short shortValue() {
    return value;
  }
}
