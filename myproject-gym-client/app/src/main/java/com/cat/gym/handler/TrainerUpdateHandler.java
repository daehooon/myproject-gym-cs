package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Member;
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
            "select m.no,m.name from gym_member_trainer mt"
                + " inner join gym_member m on mt.member_no=m.no"
                + " where mt.trainer_no=?");
        PreparedStatement stmt3 = con.prepareStatement(
            "update gym_trainer set bag=?,photo=?,name=?,tel=?,cts=?,cte=? where no=?");
        PreparedStatement stmt4 = con.prepareStatement(
            "delete from gym_member_trainer where trainer_no=?");
        PreparedStatement stmt5 = con.prepareStatement(
            "insert into gym_member_trainer(member_no,trainer_no) values(?,?)")) {

      con.setAutoCommit(false);

      Trainer trainer = new Trainer();

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("해당 번호의 트레이너가 없습니다.");
          return;
        }

        trainer.setNo(no);

        trainer.setBag(Prompt.inputString(
            String.format("전문분야(%s): ", rs.getString("bag"))));
        trainer.setPhoto(Prompt.inputString(
            String.format("사진(%s): ", rs.getString("photo"))));
        trainer.setName(Prompt.inputString(
            String.format("이름(%s): ", rs.getString("name"))));
        trainer.setPhoneNumber(Prompt.inputString(
            String.format("전화번호(%s): ", rs.getString("tel"))));
        trainer.setContractS(Prompt.inputDate(
            String.format("계약 시작일(%s): ", rs.getDate("cts"))));
        trainer.setContractE(Prompt.inputDate(
            String.format("계약 종료일(%s): ", rs.getDate("cte"))));

        StringBuilder strings = new StringBuilder();

        stmt2.setInt(1, no);
        try (ResultSet membersRs = stmt2.executeQuery()) {
          while (membersRs.next()) {
            if (strings.length() > 0) {
              strings.append(",");
            }
            strings.append(membersRs.getString("name"));
          }
        }
        trainer.setMembers(memberValidator.inputMembers(
            String.format("PT 회원명[%s](완료: 빈 문자열): ", strings)));

        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
        if (!input.equalsIgnoreCase("Y")) {
          System.out.println("트레이너 정보 변경을 취소하였습니다.");
          return;
        }

        stmt3.setString(1, trainer.getBag());
        stmt3.setString(2, trainer.getPhoto());
        stmt3.setString(3, trainer.getName());
        stmt3.setString(4, trainer.getPhoneNumber());
        stmt3.setDate(5, trainer.getContractS());
        stmt3.setDate(6, trainer.getContractE());
        stmt3.setInt(7, trainer.getNo());

        stmt4.setInt(1, trainer.getNo());
        stmt4.executeUpdate();

        for (Member member : trainer.getMembers()) {
          stmt5.setInt(1, member.getNo());
          stmt5.setInt(2, trainer.getNo());
          stmt5.executeUpdate();
        }

        con.commit();

        System.out.println("트레이너 정보를 변경하였습니다.");
      }
    }
  }
}
















