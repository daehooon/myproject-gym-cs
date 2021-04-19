package com.cat.gym.handler;

import com.cat.gym.dao.PayDao;
import com.cat.util.Prompt;

public class PayDeleteHandler implements Command {

  PayDao payDao;

  public PayDeleteHandler(PayDao payDao) {
    this.payDao = payDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 삭제]");

    int no = Prompt.inputInt("결제/예약 번호: ");

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("결제/예약 삭제를 취소하였습니다.");
      return;
    }

    if (payDao.delete(no) == 0) {
      System.out.println("해당 번호의 결제/예약이 없습니다.");
    } else {
      System.out.println("결제/예약을 삭제하였습니다.");        
    }
  }
}
