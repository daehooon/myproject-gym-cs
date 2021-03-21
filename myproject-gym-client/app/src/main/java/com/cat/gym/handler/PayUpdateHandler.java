package com.cat.gym.handler;

import java.sql.Date;
import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class PayUpdateHandler implements Command {

  Statement stmt;

  public PayUpdateHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 변경]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    String[] fields = stmt.executeQuery("pay/select", id).next().split(",");

    String rental = Prompt.inputString(String.format("운동복 대여(%s): ", fields[1]));
    String locker = Prompt.inputString(String.format("개인 락커 예약(%s): ", fields[2]));
    Date startDate = Prompt.inputDate(String.format("시작일(%s): ", fields[3]));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("결제/예약 변경을 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("pay/update",
        String.format("%s,%s,%s,%s",
            id, rental, locker, startDate));

    System.out.println("결제/예약을 변경하였습니다.");
    System.out.println();
  }
}
