package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayDetailHandler implements Command {

  Statement stmt;

  public PayDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 정보]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    String[] fields = stmt.executeQuery("pay/select", id).next().split(",");

    System.out.printf("회원권: %s\n", Pay.getSelectLabel(Integer.parseInt(fields[1])));
    System.out.printf("신규 회원: %s\n", fields[2]);
    System.out.printf("운동복 대여: %s\n", fields[3]);
    System.out.printf("개인 락커 예약: %s\n", fields[4]);
    System.out.printf("시작일: %s\n", fields[5]);
    System.out.println();
  }
}
