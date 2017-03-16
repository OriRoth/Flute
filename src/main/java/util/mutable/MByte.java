package util.mutable;

public class MByte extends M<Byte> {
  public MByte(Byte value) {
    super(value);
  }
  
  public byte byteValue() {
    return value;
  }
}
