package ru.stqa.train.mantis.tests;

import org.testng.annotations.Test;

public class FirstTest extends TestBase {

  @Test(enabled = false)
  public void firstTest() {
    System.out.print("!");
  }

}
