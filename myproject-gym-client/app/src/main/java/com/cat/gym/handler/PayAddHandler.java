package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayAddHandler implements Command {

  MemberValidator memberValidator;

  public PayAddHandler(MemberValidator memberValidator) {
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 신청]");

    Pay p = new Pay();

    p.setMemberName(memberValidator.inputMember("회원명(취소: 빈 문자열): "));
    if (p.getMemberName() == null) {
      System.out.println("결제/예약을 취소합니다.");
      return;
    }

    p.setMembership(Prompt.inputInt(
        "회원권 선택\n"
            + "0: 1개월(80,000원)\n"
            + "1: 3개월(90,000원)\n"
            + "2: 6개월(150,000원)\n"
            + "3: 1년(240,000원)\n"
            + "> "));

    p.setNewMember(Prompt.inputInt(
        "신규회원 - 가입비 33,000원(0: No | 1: Yes): "));
    p.setRental(Prompt.inputInt(
        "운동복 대여 - 월 1만원(0: No | 1: Yes): "));
    p.setLocker(Prompt.inputInt(
        "개인 락커 대여 - 월 1만원(0: No | 1: Yes): "));
    p.setPt(Prompt.inputInt(
        "PT 예약 - [주2회/1시간] 월 336,000원(0: No | 1: Yes): "));

    p.setStartDate(Prompt.inputDate("운동 시작일(yyyy-MM-dd): "));
    p.setEndDate(Prompt.inputDate("운동 종료일(yyyy-MM-dd): "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into gym_pay(mrn,mrs,nmr,rental,locker,pt,sdt,edt)"
                + " values(?,?,?,?,?,?,?,?)")) {

      stmt.setString(1, p.getMemberName());
      stmt.setInt(2, p.getMembership());
      stmt.setInt(3, p.getNewMember());
      stmt.setInt(4, p.getRental());
      stmt.setInt(5, p.getLocker());
      stmt.setInt(6, p.getPt());
      stmt.setDate(7, p.getStartDate());
      stmt.setDate(8, p.getEndDate());

      stmt.executeUpdate();

      System.out.println("결제/예약이 완료되었습니다.");
    }
  }
}













