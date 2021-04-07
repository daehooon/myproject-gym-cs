package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerUpdateHandler implements Command {

  TrainerDao trainerDao;
  MemberValidator memberValidator;

  public TrainerUpdateHandler(TrainerDao trainerDao, MemberValidator memberValidator) {
    this.trainerDao = trainerDao;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 정보 변경]");

    int no = Prompt.inputInt("트레이너 번호: ");

    Trainer trainer = trainerDao.findByNo(no);

    if (trainer == null) {
      System.out.println("해당 번호의 트레이너가 없습니다.");
      return;
    }

    trainer.setBag(Prompt.inputString(
        String.format("전문분야(%s): ", trainer.getBag())));
    trainer.setPhoto(Prompt.inputString(
        String.format("사진(%s): ", trainer.getPhoto())));
    trainer.setName(Prompt.inputString(
        String.format("이름(%s): ", trainer.getName())));
    trainer.setPhoneNumber(Prompt.inputString(
        String.format("전화번호(%s): ", trainer.getPhoneNumber())));
    trainer.setContractS(Prompt.inputDate(
        String.format("계약 시작일(%s): ", trainer.getContractS())));
    trainer.setContractE(Prompt.inputDate(
        String.format("계약 종료일(%s): ", trainer.getContractE())));

    StringBuilder strBuilder = new StringBuilder();
    List<Member> members = trainer.getMembers();
    for (Member m : members) {
      if (strBuilder.length() > 0) {
        strBuilder.append("/");
      }
      strBuilder.append(m.getName());
    }

    trainer.setMembers(memberValidator.inputMembers(
        String.format("PT 회원명[%s](완료: 빈 문자열): ", strBuilder)));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("트레이너 정보 변경을 취소하였습니다.");
      return;
    }

    trainerDao.update(trainer);

    System.out.println("트레이너 정보를 변경하였습니다.");
  }
}
















