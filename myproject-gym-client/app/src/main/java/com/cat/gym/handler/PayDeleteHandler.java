package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayDeleteHandler extends AbstractPayHandler {

  public PayDeleteHandler(List<Pay> payList) {
    super(payList);
  }

  @Override
  public void service() {
    System.out.println("[결제/예약 취소]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");

    Pay pay = findById(id);
    if (pay == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      payList.remove(pay);
      System.out.println("결제/예약을 취소하였습니다.");
      System.out.println();

    } else {
      System.out.println("결제/예약 취소가 진행되지 않았습니다.");
      System.out.println();
    }
  }

}
