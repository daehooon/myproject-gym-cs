package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class PayDeleteHandler implements Command {

  Statement stmt;

  public PayDeleteHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 취소]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    stmt.executeQuery("pay/select", id);

    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("결제/예약 취소가 진행되지 않았습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("pay/delete", id);

    System.out.println("결제/예약을 취소하였습니다.");
    System.out.println();
  }
}
