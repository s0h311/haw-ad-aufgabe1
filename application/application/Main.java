package application;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    List.of("hello", "darkness", "my", "old", "friend").stream().forEach(System.out::println);
  }
}
