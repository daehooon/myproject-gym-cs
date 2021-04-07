package com.cat.gym.handler;

import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerAddHandler implements Command {

  TrainerDao trainerDao;
  MemberValidator memberValidator;

  public TrainerAddHandler(TrainerDao trainerDao, MemberValidator memberValidator) {
    this.trainerDao = trainerDao;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 등록]");

    Trainer t = new Trainer();

    t.setBag(Prompt.inputString("전문분야: "));
    t.setPhoto(Prompt.inputString("사진: "));
    t.setName(Prompt.inputString("이름: "));
    t.setPhoneNumber(Prompt.inputString("전화번호: "));
    t.setContractS(Prompt.inputDate("계약 시작일(yyyy-MM-dd): "));
    t.setContractE(Prompt.inputDate("계약 종료일(yyyy-MM-dd): "));

    t.setMembers(memberValidator.inputMembers("PT 회원명(완료: 빈 문자열): "));

    trainerDao.insert(t);

    System.out.println("신규 트레이너님 환영합니다..!");
  }
}











