package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.cat.util.Prompt;

public class TrainerDeleteHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 삭제]");

    int no = Prompt.inputInt("트레이너 번호: ");

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("트레이너 삭제를 취소하였습니다.");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "delete from gym_trainer where no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        System.out.println("해당 번호의 트레이너가 없습니다.");
      } else {
        System.out.println("트레이너를 삭제하였습니다.");
      }
    }
  }
}
