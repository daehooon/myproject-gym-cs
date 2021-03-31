package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerUpdateHandler implements Command {

  MemberValidator memberValidator;

  public TrainerUpdateHandler(MemberValidator memberValidator) {
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 정보 변경]");

    int no = Prompt.inputInt("트레이너 번호: ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select * from gym_trainer where no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update gym_trainer set bag=?,photo=?,name=?,tel=?,cts=?,cte=?,members=? where no=?")) {

      Trainer trainer = new Trainer();

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("해당 번호의 트레이너가 없습니다.");
          return;
        }

        trainer.setNo(no);
        trainer.setBag(rs.getString("bag"));
        trainer.setPhoto(rs.getString("photo"));
        trainer.setName(rs.getString("name"));
        trainer.setPhoneNumber(rs.getString("tel"));
        trainer.setContractS(rs.getDate("cts"));
        trainer.setContractE(rs.getDate("cte"));
        trainer.setMembers(rs.getString("members"));
      }

      trainer.setBag(Prompt.inputString(String.format("전문분야(%s): ", trainer.getBag())));
      trainer.setPhoto(Prompt.inputString(String.format("사진(%s): ", trainer.getPhoto())));
      trainer.setName(Prompt.inputString(String.format("이름(%s): ", trainer.getName())));
      trainer.setPhoneNumber(Prompt.inputString(String.format("전화번호(%s): ", trainer.getPhoneNumber())));
      trainer.setContractS(Prompt.inputDate(String.format("계약 시작일(%s): ", trainer.getContractS())));
      trainer.setContractE(Prompt.inputDate(String.format("계약 종료일(%s): ", trainer.getContractE())));
      trainer.setMembers(memberValidator.inputMembers(
          String.format("PT 회원명[%s](완료: 빈 문자열): ", trainer.getMembers())));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (!input.equalsIgnoreCase("Y")) {
        System.out.println("트레이너 정보 변경을 취소하였습니다.");
        return;
      }

      stmt2.setString(1, trainer.getBag());
      stmt2.setString(2, trainer.getPhoto());
      stmt2.setString(3, trainer.getName());
      stmt2.setString(4, trainer.getPhoneNumber());
      stmt2.setDate(5, trainer.getContractS());
      stmt2.setDate(6, trainer.getContractE());
      stmt2.setString(7, trainer.getMembers());
      stmt2.setInt(8, trainer.getNo());

      stmt2.executeUpdate();

      System.out.println("트레이너 정보를 변경하였습니다.");
    }
  }
}
















