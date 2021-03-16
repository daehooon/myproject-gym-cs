package com.cat.gym.handler;

import java.util.Iterator;
import java.util.List;
import com.cat.gym.domain.Trainer;

public class TrainerListHandler extends AbstractTrainerHandler {

  public TrainerListHandler(List<Trainer> trainerList) {
    super(trainerList);
  }

  @Override
  public void service() {
    System.out.println("[트레이너 목록]");
    System.out.println();

    Iterator<Trainer> iterator = trainerList.iterator();

    while (iterator.hasNext()) {
      Trainer t = iterator.next();
      System.out.printf("%d %s %s\n", t.getNo(), t.getName(), t.getPhoneNumber());
      System.out.println();
    }
  }

}
