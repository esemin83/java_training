package ru.stqa.train.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SquareTests {

  @Test
  public void testsSqr() {
    Square s = new Square(6);
    //assert s.sqr() == 36;
    Assert.assertEquals((s.sqr()), 36.0);
  }
}
