package ru.stqa.train.sandbox;

public class Program {
  public static void main(String[] args) {
    Point p1 = new Point(341, 500);
    Point p2 = new Point(201, 765);

    System.out.println("\n" + "Расстояние между точками" + "\n" + "x1 = " + p1.x + " y1 = " + p1.y + "\n" + "x2 = "
            + p2.x + " y2 = " + p2.y + "\n" + "есть " + p1.distance(p1, p2));


    Point a1 = new Point(132, 852);
    Point a2 = new Point(831, 753);

    System.out.println("\n" + "Расстояние между точками" + "\n" + "x1 = " + a1.x + " y1 = " + a1.y + "\n" + "x2 = "
            + a2.x + " y2 = " + a2.y + "\n" + "есть " + a2.distance(a1, a2));
  }
}
