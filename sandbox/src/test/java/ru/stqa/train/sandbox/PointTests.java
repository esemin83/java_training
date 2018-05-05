package ru.stqa.train.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point a1 = new Point(341, 500);
    Point a2 = new Point(201, 765);
    double dist = a1.distance(a1, a2);
    Assert.assertEquals(dist, 299.7081914129142);

  }

  @Test
  public void testDistanceNew() {
    Point a1 = new Point(426, 982);
    Point a2 = new Point(753, 855);
    double dist = a1.distance(a1, a2);
    Assert.assertEquals(dist, 350.7962371519968);

  }

  @Test
  public void testDistanceOne() {
    Point a1 = new Point(-7, 56);
    Point a2 = new Point(0, -795);
    double dist = a1.distance(a1, a2);
    Assert.assertEquals(dist, 851.0287891722583);

  }

  @Test
  public void testDistanceOneMore() {
    Point a1 = new Point(0, 5);
    Point a2 = new Point(0, 5);
    double dist = a1.distance(a1, a2);
    Assert.assertEquals(dist, 0.0);

  }
}
