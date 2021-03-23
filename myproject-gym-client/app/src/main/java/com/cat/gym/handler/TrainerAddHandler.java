package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerAddHandler implements Command {

  Statement stmt;
  MemberValidator memberValidator;

  public TrainerAddHandler(Statement stmt, MemberValidator memberValidator) {
    this.stmt = stmt;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 등록]");
    System.out.println();

    Trainer t = new Trainer();
    t.setBag(Prompt.inputString("전문분야: "));
    t.setPhoto(Prompt.inputString("사진: "));
    t.setName(Prompt.inputString("이름: "));
    t.setPhoneNumber(Prompt.inputString("전화번호: "));
    t.setContractS(Prompt.inputDate("계약 시작일(yyyy-MM-dd): "));
    t.setContractE(Prompt.inputDate("계약 종료일(yyyy-MM-dd): "));

    t.setMembers(memberValidator.inputMembers("PT회원 ID등록(완료: 빈 문자열): "));

    stmt.executeUpdate("trainer/insert",
        String.format("%s,%s,%s,%s,%s,%s,%s",
            t.getBag(),
            t.getPhoto(),
            t.getName(),
            t.getPhoneNumber(),
            t.getContractS(),
            t.getContractE(),
            t.getMembers()));

    System.out.println();
    System.out.println("신규 트레이너님 환영합니다..!");
    System.out.println();
  }
}