package flute.util.mutable;

/**
 * Mutable byte.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MByte extends M<Byte> {
  public MByte(Byte value) {
    super(value);
  }

  public byte byteValue() {
    return value;
  }
}
