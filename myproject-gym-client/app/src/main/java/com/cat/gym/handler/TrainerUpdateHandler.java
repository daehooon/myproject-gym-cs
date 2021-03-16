package com.cat.gym.handler;

import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerUpdateHandler extends AbstractTrainerHandler {

  private MemberValidator memberValidatorHandler;

  public TrainerUpdateHandler(List<Trainer> trainerList, MemberValidator memberValidatorHandler) {
    super(trainerList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[트레이너 정보 변경]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    Trainer trainer = findByNo(no);
    if (trainer == null) {
      System.out.println();
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }

    while(true) {
      String bag = Prompt.inputString(String.format("전문 분야(%s): ", trainer.getBag()));
      String photo = Prompt.inputString(String.format("사진(%s): ", trainer.getPhoto()));
      String name = Prompt.inputString(String.format("이름(%s): ", trainer.getName()));
      String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", trainer.getPhoneNumber()));
      Date contractE = Prompt.inputDate(String.format("계약 종료일(%s): ", trainer.getContractE()));
      String members = memberValidatorHandler.inputMembers(String.format("현재 PT회원 ID목록[%s]: ", trainer.getMembers()));

      if (bag.equals("") ||
          photo.equals("") ||
          name.equals("") ||
          phoneNumber.equals("")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력해 주세요.");
        System.out.println();
        continue;
      } else {
        System.out.println();
        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
        System.out.println();

        if (input.equalsIgnoreCase("Y")) {
          trainer.setBag(bag);
          trainer.setPhoto(photo);
          trainer.setName(name);
          trainer.setPhoneNumber(phoneNumber);
          trainer.setContractE(contractE);
          trainer.setMembers(members);
          System.out.println("정보를 변경하였습니다.");
          System.out.println();

        } else {
          System.out.println("정보 변경을 취소하였습니다.");
          System.out.println();
        }
      }
      break;
    }
  }

}
