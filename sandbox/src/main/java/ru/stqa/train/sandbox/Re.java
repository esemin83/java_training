package ru.stqa.train.sandbox;

import java.util.Arrays;

public class Re {

  public static void main(String[] args) {
    String str = "Select (group_new)";
    String res[] = str.split("[()]");
    System.out.println(Arrays.toString(res));
    System.out.println(res[1]);

  }
}
