package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.PayDao;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayListHandler implements Command {

  PayDao payDao;

  public PayListHandler(PayDao payDao) {
    this.payDao = payDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 목록]");

    String input = Prompt.inputString("트레이너 번호(전체: 빈 문자열 또는 0) ");

    int trainerNo = 0;
    try {
      if (input.length() != 0) {
        trainerNo = Integer.parseInt(input);
      }
    } catch (Exception e) {
      System.out.println("트레이너 번호를 입력하세요.");
    }

    List<Pay> pays = null;
    if (trainerNo == 0) {
      pays = payDao.findAll();
    } else {
      pays = payDao.findByTrainerNo(trainerNo);
    }

    if (pays.size() == 0) {
      System.out.println("해당 번호의 트레이너가 없거나 또는 등록된 트레이너가 없습니다.");
      return;
    }

    trainerNo = 0;
    for (Pay p : pays) {
      if (trainerNo != p.getTrainerNo()) {
        System.out.printf("'%s' 트레이너 목록: \n", p.getTrainerName());
        trainerNo = p.getTrainerNo();
      }
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          p.getNo(),
          p.getOwner().getName(),
          Pay.getMembershipLabel(p.getMembership()),
          Pay.getPtLabel(p.getPt()),
          p.getStartDate(),
          p.getEndDate());

    }
  }
}











