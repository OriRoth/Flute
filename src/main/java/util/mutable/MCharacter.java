package util.mutable;

/**
 * Mutable character.
 * 
 * @author Ori Roth
 * @since Mar 18, 2017
 */
public class MCharacter extends M<Character> {
  public MCharacter(Character value) {
    super(value);
  }

  public char charValue() {
    return value;
  }
}
