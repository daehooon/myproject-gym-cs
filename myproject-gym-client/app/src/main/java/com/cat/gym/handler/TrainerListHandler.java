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
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %s, [%s]\n", 
            rs.getInt("no"), 
            rs.getString("photo"),
            rs.getString("name"),
            rs.getString("tel"),
            rs.getDate("cte"),
            rs.getString("members"));
      }
    }
  }
}
