package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayAddHandler extends AbstractPayHandler {

  private MemberValidator memberValidatorHandler;

  public PayAddHandler(List<Pay> payList, MemberValidator memberValidatorHandler) {
    super(payList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[결제/예약 신청]");
    System.out.println();

    Pay p = new Pay();

    p.setId(memberValidatorHandler.inputMember("아이디(취소: 빈 문자열): "));

    if (p.getId() == null) {
      System.out.println();
      System.out.println("결제/예약을 취소합니다.");
      System.out.println();
      return;
    }

    while (true) {
      p.setSelect(Prompt.inputInt("회원권 선택\n"
          + "0: 1개월(80,000원)\n"
          + "1: 3개월(90,000원)\n"
          + "2: 6개월(150,000원)\n"
          + "3: 1년(240,000원)\n"
          + "> "));
      p.setJoin(Prompt.inputString("신규 회원 - 가입비 33,000원(O/X): "));
      p.setRental(Prompt.inputString("운동복 대여 - 월 1만원(O/X): "));
      p.setLocker(Prompt.inputString("개인 락커 예약 - 월 1만원(O/X): "));
      p.setStartDate(Prompt.inputDate("시작일(yyyy-MM-dd): "));

      if (!p.getJoin().equalsIgnoreCase("o") && !p.getJoin().equalsIgnoreCase("x")
          || !p.getRental().equalsIgnoreCase("o") && !p.getRental().equalsIgnoreCase("x")
          || !p.getLocker().equalsIgnoreCase("o") && !p.getLocker().equalsIgnoreCase("x")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력하지 않았거나 "
            + "올바른 값이 입력되지 않았습니다.");
        System.out.println("다시 입력해 주세요.");
        System.out.println();
        continue;
      } else {
        payList.add(p);
        break;
      }
    }
    System.out.println();
    System.out.println("결제/예약이 완료되었습니다.");
    System.out.println();
  }

}













