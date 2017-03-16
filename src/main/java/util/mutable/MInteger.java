package util.mutable;

public class MInteger extends M<Integer> {
  public MInteger(Integer value) {
    super(value);
  }
  
  public int intValue() {
    return value;
  }
}
