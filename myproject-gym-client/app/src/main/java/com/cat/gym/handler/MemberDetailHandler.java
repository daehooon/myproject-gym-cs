package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class MemberDetailHandler implements Command {

  Statement stmt;

  public MemberDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception{
    System.out.println("[회원 정보]");
    System.out.println();

    int no = Prompt.inputInt("번호: ");

    String[] fields = stmt.executeQuery("member/select", Integer.toString(no)).next().split(",");

    System.out.printf("이름: %s\n", fields[1]);
    System.out.printf("전화번호: %s\n", fields[2]);
    System.out.printf("주소: %s\n", fields[3]);
    System.out.printf("아이디: %s\n", fields[4]);
    System.out.printf("비밀번호: %s\n", fields[5]);
    System.out.printf("가입일: %s\n", fields[6]);
    System.out.println();
  }
}
