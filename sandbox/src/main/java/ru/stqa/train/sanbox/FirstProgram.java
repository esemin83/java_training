package ru.stqa.train.sanbox;

public class FirstProgram {

  public static void main(String[] args) {
    String value = "bonjour";
    ptr(value);

    //System.out.println(sum(4,8));
    double l = sum(4, 8);
    System.out.println(l);

    double a = 1;
    double b = 5;
    System.out.println("Сумма " + a + " + " + b + " = " + sum(a, b));

    double c = 5;
    System.out.println("Квадрат " + c + " = " + sum(c));

  }

  public static void ptr(String val) {
    System.out.println("Print some value " + "'" + val + "'");
  }

  public static double sum(double a, double b) {
    return a + b;
  }

  public static double sum(double c) {
    return c * c;
  }
}