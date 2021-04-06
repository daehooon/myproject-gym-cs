package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayDetailHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 정보]");

    int no = Prompt.inputInt("결제/예약 번호: ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select"
                + " p.no,"
                + " p.mrs,"
                + " p.nmr,"
                + " p.rental,"
                + " p.locker,"
                + " p.pt,"
                + " p.sdt,"
                + " p.edt,"
                + " m.no as owner_no,"
                + " m.name as owner_name"
                + " from gym_pay p"
                + " inner join gym_member m on p.owner=m.no"
                + " where p.no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("해당 번호의 결제/예약이 없습니다.");
          return;
        }

        System.out.printf("회원명: %s\n", rs.getString("owner_name"));
        System.out.printf("회원권: %s\n", Pay.getMembershipLabel(rs.getInt("mrs")));
        System.out.printf("신규회원: %s\n", Pay.getNewMemberLabel( rs.getInt("nmr")));
        System.out.printf("운동복 대여: %s\n", Pay.getRentalLabel(rs.getInt("rental")));
        System.out.printf("개인 락커 대여: %s\n", Pay.getLockerLabel(rs.getInt("locker")));
        System.out.printf("PT 예약: %s\n", Pay.getPtLabel((rs.getInt("pt"))));
        System.out.printf("운동 시작일: %s\n", rs.getDate("sdt"));
        System.out.printf("운동 종료일: %s\n", rs.getDate("edt"));
      }
    }
  }
}
