package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerAddHandler implements Command {

  MemberValidator memberValidator;

  public TrainerAddHandler(MemberValidator memberValidator) {
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 등록]");

    Trainer t = new Trainer();

    t.setBag(Prompt.inputString("전문분야: "));
    t.setPhoto(Prompt.inputString("사진: "));
    t.setName(Prompt.inputString("이름: "));
    t.setPhoneNumber(Prompt.inputString("전화번호: "));
    t.setContractS(Prompt.inputDate("계약 시작일(yyyy-MM-dd): "));
    t.setContractE(Prompt.inputDate("계약 종료일(yyyy-MM-dd): "));

    t.setMembers(memberValidator.inputMembers("PT 회원명(완료: 빈 문자열): "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into gym_trainer(bag,photo,name,tel,cts,cte,members)"
                + " values(?,?,?,?,?,?,?)")) {

      stmt.setString(1, t.getBag());
      stmt.setString(2, t.getPhoto());
      stmt.setString(3, t.getName());
      stmt.setString(4, t.getPhoneNumber());
      stmt.setDate(5, t.getContractS());
      stmt.setDate(6, t.getContractE());
      stmt.setString(7, t.getMembers());

      stmt.executeUpdate();

      System.out.println("신규 트레이너님 환영합니다..!");
    }
  }
}











