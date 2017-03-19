package flute.util.mutable;

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

  public int postIncreament() {
    int ret = get();
    set(ret + 1);
    return ret;
  }

  public int preIncreament() {
    return postIncreament() + 1;
  }
}
