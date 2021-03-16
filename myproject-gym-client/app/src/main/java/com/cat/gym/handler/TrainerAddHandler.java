package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerAddHandler extends AbstractTrainerHandler {

  private MemberValidator memberValidatorHandler;

  public TrainerAddHandler(List<Trainer> trainerList, MemberValidator memberValidatorHandler) {
    super(trainerList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[트레이너 등록]");
    System.out.println();

    Trainer t = new Trainer();

    while(true) {
      t.setNo(Prompt.inputInt("등록 번호: "));
      t.setBag(Prompt.inputString("전문분야: "));
      t.setPhoto(Prompt.inputString("사진: "));
      t.setName(Prompt.inputString("이름: "));
      t.setPhoneNumber(Prompt.inputString("전화번호: "));
      t.setContractS(Prompt.inputDate("계약 시작일(yyyy-MM-dd): "));
      t.setContractE(Prompt.inputDate("계약 종료일(yyyy-MM-dd): "));

      t.setMembers(memberValidatorHandler.inputMembers(
          String.format("PT회원 ID등록(완료: 빈 문자열): ", t.getMembers())));

      if (t.getBag().equals("") ||
          t.getPhoto().equals("") ||
          t.getName().equals("") ||
          t.getPhoneNumber().equals("")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력해 주세요.");
        System.out.println();
      } else {
        trainerList.add(t);
        break;
      }
    }
    System.out.println();
    System.out.println("신규 트레이너님 환영합니다..!");
    System.out.println();
  }

}
