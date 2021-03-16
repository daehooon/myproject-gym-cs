package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class TrainerDeleteHandler extends AbstractTrainerHandler {

  public TrainerDeleteHandler(List<Trainer> trainerList) {
    super(trainerList);
  }

  @Override
  public void service() {
    System.out.println("[트레이너 삭제]");
    System.out.println();

    int no = Prompt.inputInt("등록 번호: ");

    Trainer trainer = findByNo(no);
    if (trainer == null) {
      System.out.println();
      System.out.println("해당 번호의 트레이너가 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      trainerList.remove(trainer);
      System.out.println("트레이너를 삭제하였습니다.");
      System.out.println();

    } else {
      System.out.println("트레이너 삭제를 취소하였습니다.");
      System.out.println();
    }
  }

}
