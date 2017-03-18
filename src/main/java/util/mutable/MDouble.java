package util.mutable;

/**
 * Mutable double.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MDouble extends M<Double> {
  public MDouble(Double value) {
    super(value);
  }

  public double doubleValue() {
    return value;
  }
}
