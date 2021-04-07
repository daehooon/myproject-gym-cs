package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerDetailHandler implements Command {

  TrainerDao trainerDao;

  public TrainerDetailHandler(TrainerDao trainerDao) {
    this.trainerDao = trainerDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 정보]");

    int no = Prompt.inputInt("트레이너 번호: ");

    Trainer trainer = trainerDao.findByNo(no);

    if (trainer == null) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      return;
    }

    System.out.printf("전문분야: %s\n", trainer.getBag());
    System.out.printf("사진: %s\n", trainer.getPhoto());
    System.out.printf("이름: %s\n", trainer.getName());
    System.out.printf("전화번호: %s\n", trainer.getPhoneNumber());
    System.out.printf("계약 시작일: %s\n", trainer.getContractS());
    System.out.printf("계약 종료일: %s\n", trainer.getContractE());

    StringBuilder strBuilder = new StringBuilder();
    List<Member> members = trainer.getMembers();
    for (Member m : members) {
      if (strBuilder.length() > 0) {
        strBuilder.append("/");
      }
      strBuilder.append(m.getName());
    }

    System.out.printf("PT 회원명: %s\n", strBuilder.toString());
  }
}







