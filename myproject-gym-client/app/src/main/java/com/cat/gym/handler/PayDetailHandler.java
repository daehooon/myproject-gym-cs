package com.cat.gym.handler;

import com.cat.gym.dao.PayDao;
import com.cat.gym.domain.Pay;
import com.cat.util.Prompt;

public class PayDetailHandler implements Command {

  PayDao payDao;

  public PayDetailHandler(PayDao payDao) {
    this.payDao = payDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 정보]");

    int no = Prompt.inputInt("결제/예약 번호: ");

    Pay pay = payDao.findByNo(no);

    if (pay == null) {
      System.out.println("해당 번호의 결제/예약이 없습니다.");
      return;
    }

    System.out.printf("회원명: %s\n", pay.getOwner().getName());
    System.out.printf("회원권: %s\n", Pay.getMembershipLabel(pay.getMembership()));
    System.out.printf("신규회원: %s\n", Pay.getNewMemberLabel(pay.getNewMember()));
    System.out.printf("운동복 대여: %s\n", Pay.getRentalLabel(pay.getRental()));
    System.out.printf("개인 락커 대여: %s\n", Pay.getLockerLabel(pay.getLocker()));
    System.out.printf("PT 예약: %s\n", Pay.getPtLabel((pay.getPt())));
    System.out.printf("담당 트레이너: %s\n", pay.getTrainerName());
    System.out.printf("운동 시작일: %s\n", pay.getStartDate());
    System.out.printf("운동 종료일: %s\n", pay.getEndDate());
  }
}
