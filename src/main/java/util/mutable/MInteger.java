package util.mutable;

/**
 * Mutable int.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MInteger extends M<Integer> {
  public MInteger(Integer value) {
    super(value);
  }

  public int intValue() {
    return value;
  }
}
