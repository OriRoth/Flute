package util.mutable;

/**
 * Mutable short.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MShort extends M<Short> {
  public MShort(Short value) {
    super(value);
  }

  public short shortValue() {
    return value;
  }
}
