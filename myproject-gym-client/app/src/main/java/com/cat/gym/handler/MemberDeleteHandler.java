package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class MemberDeleteHandler implements Command {

  Statement stmt;

  public MemberDeleteHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 탈퇴]");
    System.out.println();

    int no = Prompt.inputInt("번호: ");
    System.out.println();

    stmt.executeQuery("member/select", Integer.toString(no));

    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("member/delete", Integer.toString(no));

    System.out.println("회원을 탈퇴하였습니다.");
    System.out.println();
  }
}
