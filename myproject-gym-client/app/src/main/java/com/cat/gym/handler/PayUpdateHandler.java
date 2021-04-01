package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayUpdateHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 변경]");

    int no = Prompt.inputInt("결제/예약 번호: ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,rental,locker,pt,sdt,edt from gym_pay where no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update gym_pay set rental=?,locker=?,pt=?,sdt=?,edt=? where no=?")) {

      Pay pay = new Pay();

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("해당 번호의 결제/예약이 없습니다.");
          return;
        }

        pay.setNo(no);
        pay.setRental(rs.getInt("rental"));
        pay.setLocker(rs.getInt("locker"));
        pay.setPt(rs.getInt("pt"));
        pay.setStartDate(rs.getDate("sdt"));
        pay.setEndDate(rs.getDate("edt"));
      }

      pay.setRental(Prompt.inputInt(String.format(
          "운동복 대여(%s) (0: No | 1: Yes): ", Pay.getRentalLabel(pay.getRental()))));
      pay.setLocker(Prompt.inputInt(String.format(
          "개인 락커 대여(%s) (0: No | 1: Yes): ", Pay.getLockerLabel(pay.getLocker()))));
      pay.setPt(Prompt.inputInt(String.format(
          "PT 예약(%s) (0: No | 1: Yes): ", Pay.getPtLabel(pay.getPt()))));
      pay.setStartDate(Prompt.inputDate(String.format(
          "운동 시작일(%s): ", pay.getStartDate())));
      pay.setEndDate(Prompt.inputDate(String.format(
          "운동 종료일(%s): ", pay.getEndDate())));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (!input.equalsIgnoreCase("Y")) {
        System.out.println("결제/예약 변경을 취소하였습니다.");
        return;
      }

      stmt2.setInt(1, pay.getRental());
      stmt2.setInt(2, pay.getLocker());
      stmt2.setInt(3, pay.getPt());
      stmt2.setDate(4, pay.getStartDate());
      stmt2.setDate(5, pay.getEndDate());
      stmt2.setInt(6, pay.getNo());
      stmt2.executeUpdate();

      System.out.println("결제/예약을 변경하였습니다.");
    }
  }
}








