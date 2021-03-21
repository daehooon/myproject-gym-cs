package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class TrainerDetailHandler implements Command {

  Statement stmt;

  public TrainerDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 정보]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    String[] fields = stmt.executeQuery("trainer/select", Integer.toString(no)).next().split(",");

    System.out.printf("전문 분야: %s\n", fields[1]);
    System.out.printf("사진: %s\n", fields[2]);
    System.out.printf("이름: %s\n", fields[3]);
    System.out.printf("전화번호: %s\n", fields[4]);
    System.out.printf("계약 시작일: %s\n", fields[5]);
    System.out.printf("계약 종료일: %s\n", fields[6]);
    System.out.printf("PT회원 ID목록: %s\n", fields[7]);
    System.out.println();
  }
}
