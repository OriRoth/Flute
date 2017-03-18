package util.ui;

import static util.ui.FluentPrinter.*;

import org.junit.Test;

import util.mutable.MInteger;

public class FluentPrinterTest {
  @Test
  public void a() {
    int x = printer(new MInteger(1)) //
        .print() //
        .d0(i -> i.accept(2)) //
        .print(i -> "The current value is: " + i) //
        .d0(i -> i.accept(3)) //
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
