package com.cat.gym.handler;

import java.util.Iterator;
import com.cat.driver.Statement;
import com.cat.gym.domain.Pay;

public class PayListHandler implements Command {

  Statement stmt;

  public PayListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 목록]");
    System.out.println();

    Iterator<String> results = stmt.executeQuery("pay/selectall");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      System.out.printf("%s, %s, %s\n",
          fields[0], Pay.getSelectLabel(Integer.parseInt(fields[1])), fields[2]);
      System.out.println();
    }
  }
}
