package ru.stqa.train.sandbox;


public class FirstProgram {

  public static void main(String[] args) {
    String value = "bonjour";
    ptr(value);

    Rectangle r = new Rectangle(3, 5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.rect());

    Square c = new Square(5);
    System.out.println("Площадь квадрата со стороной " + c.l + " = " + c.sqr());

  }

  public static void ptr(String val) {
    System.out.println("Print some value " + "'" + val + "'");
  }
}