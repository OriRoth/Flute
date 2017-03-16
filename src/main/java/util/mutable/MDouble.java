package util.mutable;

public class MDouble extends M<Double> {
  public MDouble(Double value) {
    super(value);
  }

  public double doubleValue() {
    return value;
  }
}
