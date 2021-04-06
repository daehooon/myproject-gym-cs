package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainerListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 목록]");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,photo,name,tel,cte,members from gym_trainer order by name asc");
        PreparedStatement stmt2 = con.prepareStatement(
            "select m.no,m.name from gym_member_trainer mt"
                + " inner join gym_member m on mt.member_no=m.no"
                + " where mt.trainer_no=?");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        stmt2.setInt(1, rs.getInt("no"));
        String members = "";
        try (ResultSet membersRs = stmt2.executeQuery()) {
          while (membersRs.next()) {
            if (members.length() > 0) {
              members += "/";
            }
            members += membersRs.getString("name");
          }
        }

        System.out.printf("%d, %s, %s, %s, %s, [%s]\n", 
            rs.getInt("no"), 
            rs.getString("photo"),
            rs.getString("name"),
            rs.getString("tel"),
            rs.getDate("cte"),
            members);
      }
    }
  }
}
