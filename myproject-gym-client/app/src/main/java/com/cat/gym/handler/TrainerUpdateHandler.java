package com.cat.gym.handler;

import java.sql.Date;
import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class TrainerUpdateHandler implements Command {

  Statement stmt;
  MemberValidator memberValidator;

  public TrainerUpdateHandler(Statement stmt, MemberValidator memberValidator) {
    this.stmt = stmt;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 정보 변경]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    String[] fields = stmt.executeQuery("trainer/select", Integer.toString(no)).next().split(",");

    String bag = Prompt.inputString(String.format("전문 분야(%s): ", fields[1]));
    String photo = Prompt.inputString(String.format("사진(%s): ", fields[2]));
    String name = Prompt.inputString(String.format("이름(%s): ", fields[3]));
    String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", fields[4]));
    Date contractE = Prompt.inputDate(String.format("계약 종료일(%s): ", fields[5]));
    String members = memberValidator.inputMembers(
        String.format("현재 PT회원 ID목록[%s]: ", fields[6]));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("트레이너 정보 변경을 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("trainer/update",
        String.format("%d,%s,%s,%s,%s,%s,%s",
            no, bag, photo, name, phoneNumber, contractE, members));

    System.out.println("트레이너 정보를 변경하였습니다.");
    System.out.println();
  }
}
