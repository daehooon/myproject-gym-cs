package com.cat.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyScan = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return keyScan.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.valueOf(inputString(title));
  }

  public static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void close() {
    keyScan.close();
  }
}
