package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class TrainerDeleteHandler implements Command {

  Statement stmt;

  public TrainerDeleteHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 삭제]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    stmt.executeQuery("trainer/select", Integer.toString(no));

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("트레이너 삭제를 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("trainer/delete", Integer.toString(no));

    System.out.println("트레이너를 삭제하였습니다.");
    System.out.println();
  }
}
