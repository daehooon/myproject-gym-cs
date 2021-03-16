package com.cat.gym.handler;

import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayUpdateHandler extends AbstractPayHandler {

  public PayUpdateHandler(List<Pay> payList) {
    super(payList);
  }

  @Override
  public void service() {
    System.out.println("[결제/예약 변경]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String rental = Prompt.inputString(String.format("운동복 대여(%s): ", pay.getRental()));
    String locker = Prompt.inputString(String.format("개인 락커 예약(%s): ", pay.getLocker()));
    Date startDate = Prompt.inputDate(String.format("시작일(%s): ", pay.getStartDate()));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      pay.setRental(rental);
      pay.setLocker(locker);
      pay.setStartDate(startDate);
      System.out.println("결제/예약을 변경하였습니다.");
      System.out.println();

    } else {
      System.out.println("결제/예약 변경을 취소하였습니다.");
      System.out.println();
    }
  }

}
