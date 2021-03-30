package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberAddHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setName(Prompt.inputString("이름: "));
    m.setEmail(Prompt.inputString("이메일: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setPhoto(Prompt.inputString("사진: "));
    m.setTel(Prompt.inputString("전화번호: "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into gym_member(name,email,password,photo,tel) values(?,?,password(?),?,?)")) {

      stmt.setString(1, m.getName());
      stmt.setString(2, m.getEmail());
      stmt.setString(3, m.getPassword());
      stmt.setString(4, m.getPhoto());
      stmt.setString(5, m.getTel());

      stmt.executeUpdate();

      System.out.println("Cat Gym 회원이 되신걸 환영합니다!");
    }
  }
}
