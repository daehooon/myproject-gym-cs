package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Pay;

public class PayListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 목록]");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            );
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %s, %s\n",
            rs.getInt("no"),
            rs.getString("owner_name"),
            Pay.getMembershipLabel(rs.getInt("mrs")),
            Pay.getPtLabel(rs.getInt("pt")),
            rs.getDate("sdt"),
            rs.getDate("edt"));
      }
    }
  }
}
