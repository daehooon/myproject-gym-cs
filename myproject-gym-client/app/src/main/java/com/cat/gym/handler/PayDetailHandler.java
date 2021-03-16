package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayDetailHandler extends AbstractPayHandler {

  public PayDetailHandler(List<Pay> payList) {
    super(payList);
  }

  @Override
  public void service() {
    System.out.println("[결제/예약 정보]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("회원권: %s\n", getSelectLabel(pay.getSelect()));
    System.out.printf("신규 회원: %s\n", pay.getJoin());
    System.out.printf("운동복 대여: %s\n", pay.getRental());
    System.out.printf("개인 락커 예약: %s\n", pay.getLocker());
    System.out.printf("시작일: %s\n", pay.getStartDate());
    System.out.println();
  }

}
