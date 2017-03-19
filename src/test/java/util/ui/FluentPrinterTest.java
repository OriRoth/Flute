package util.ui;

import static flute.util.fluent.Fluent.*;
import static flute.util.ui.FluentPrinter.*;

import org.junit.Test;

import flute.util.mutable.MInteger;

public class FluentPrinterTest {
  @Test
  public void a() {
    int x = printer(new MInteger(1)) //
        .print() //
        .d0(i -> i.set(2)) //
        .print(i -> "The current value is: " + i) //
        .d0(i -> i.set(3)) //
        .print("Some debug message") //
        .origin() //
        .intValue();
    assert x == 3;
  }

  @Test
  public void b() {
    Integer x = 1;
    assert print(x) + 1 == 2;
    assert fluent(x, _x -> System.out.println("The current value is: " + _x)) + 1 == 2;
  }
}
