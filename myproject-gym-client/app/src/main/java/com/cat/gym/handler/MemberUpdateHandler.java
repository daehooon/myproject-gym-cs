package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class MemberUpdateHandler implements Command {

  Statement stmt;

  public MemberUpdateHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 정보 변경]");
    System.out.println();

    int no = Prompt.inputInt("번호: ");
    System.out.println();

    String[] fields = stmt.executeQuery("member/select", Integer.toString(no)).next().split(",");

    String name = Prompt.inputString(String.format("이름(%s): ", fields[1]));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", fields[2]));
    String residence = Prompt.inputString(String.format("주소(%s): ", fields[3]));
    String id = Prompt.inputString(String.format("아이디(%s): ", fields[4]));
    String password = Prompt.inputString(String.format("비밀번호(%s): ", fields[5]));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("회원 정보 변경을 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("member/update",
        String.format("%d,%s,%s,%s,%s,%s",
            no, name, phoneNumber, residence, id, password));

    System.out.println("회원 정보를 변경하였습니다.");
    System.out.println();
  }
}
