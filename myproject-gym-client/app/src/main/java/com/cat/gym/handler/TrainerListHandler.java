package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;

public class TrainerListHandler implements Command {

  TrainerDao trainerDao;

  public TrainerListHandler(TrainerDao trainerDao) {
    this.trainerDao = trainerDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[트레이너 목록]");

    List<Trainer> trainers = trainerDao.findAll();

    for (Trainer t : trainers) {

      StringBuilder strBuilder = new StringBuilder();
      List<Member> members = t.getMembers();
      for (Member m : members) {
        if (strBuilder.length() > 0) {
          strBuilder.append("/");
        }
        strBuilder.append(m.getName());
      }

      System.out.printf("%d, %s, %s, %s, %s, [%s]\n", 
          t.getNo(), 
          t.getPhoto(),
          t.getName(),
          t.getPhoneNumber(),
          t.getContractE(),
          strBuilder.toString());
    }
  }
}
