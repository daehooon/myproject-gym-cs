package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayAddHandler implements Command {

  Statement stmt;
  MemberValidator memberValidator;

  public PayAddHandler(Statement stmt, MemberValidator memberValidator) {
    this.stmt = stmt;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 신청]");
    System.out.println();

    Pay p = new Pay();
    p.setId(memberValidator.inputMember("아이디(취소: 빈 문자열): "));
    if (p.getId() == null) {
      System.out.println();
      System.out.println("결제/예약을 취소합니다.");
      System.out.println();
      return;
    }

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

    stmt.executeUpdate("pay/insert",
        String.format("%s,%s,%s,%s,%s,%s",
            p.getId(),
            p.getSelect(),
            p.getJoin(),
            p.getRental(),
            p.getLocker(),
            p.getStartDate()));

    System.out.println();
    System.out.println("결제/예약이 완료되었습니다.");
    System.out.println();
  }
}













