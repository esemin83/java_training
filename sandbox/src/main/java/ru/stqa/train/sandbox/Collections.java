package ru.stqa.train.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "Ruby";

    List<String> languages = new ArrayList<>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("Ruby");

    List<String> languagesNew = Arrays.asList("Java", "C#", "Python", "Ruby");

    for (int i = 0; i < langs.length; i++) {
      System.out.println(langs[i]);
    }

    for (String l : langs) {
      System.out.println(l);
    }

    for (String k : languages) {
      System.out.println(k);
    }

    for (int i = 0; i< languagesNew.size(); i++) {
      System.out.println(languagesNew.get(i));
    }
  }
}
