package flute.util.mutable;

/**
 * Mutable boolean.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MBoolean extends M<Boolean> {

  public MBoolean(Boolean value) {
    super(value);
  }

  public boolean booleanValue() {
    return value;
  }

  public MBoolean toggle() {
    set(!get());
    return this;
  }
}
