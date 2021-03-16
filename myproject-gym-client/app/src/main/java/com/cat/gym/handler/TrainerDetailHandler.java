package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerDetailHandler extends AbstractTrainerHandler {

  public TrainerDetailHandler(List<Trainer> trainerList) {
    super(trainerList);
  }

  @Override
  public void service() {
    System.out.println("[트레이너 정보]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    Trainer trainer = findByNo(no);
    if (trainer == null) {
      System.out.println();
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("전문 분야: %s\n", trainer.getBag());
    System.out.printf("사진: %s\n", trainer.getPhoto());
    System.out.printf("이름: %s\n", trainer.getName());
    System.out.printf("전화번호: %s\n", trainer.getPhoneNumber());
    System.out.printf("계약 시작일: %s\n", trainer.getContractS());
    System.out.printf("계약 종료일: %s\n", trainer.getContractE());
    System.out.printf("PT회원 ID목록: [%s]\n", trainer.getMembers());
    System.out.println();
  }

}
