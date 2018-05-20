package ru.stqa.train.sandbox;

public class Equality {

  public static void main(String[] args) {
    String s1 = "value";
    //String s2 = s1;
    //String s2 = new String(s1);
    //String s2 = "value";
    String s2 = "val" + "ue";

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));

  }
}
