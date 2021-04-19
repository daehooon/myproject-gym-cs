package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.PayDao;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Pay;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class PayUpdateHandler implements Command {

  PayDao payDao;
  TrainerDao trainerDao;

  public PayUpdateHandler(PayDao payDao, TrainerDao trainerDao) {
    this.payDao = payDao;
    this.trainerDao = trainerDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 변경]");

    int no = Prompt.inputInt("결제/예약 번호: ");

    Pay pay = payDao.findByNo(no);
    if (pay == null) {
      System.out.println("해당 번호의 결제/예약이 없습니다.");
      return;
    }

    System.out.printf("현재 트레이너: %\n", pay.getTrainerName());

    List<Trainer> trainers = trainerDao.findAll();
    System.out.println("트레이너 목록:");
    if (trainers.size() == 0) {
      System.out.println("현재 등록된 트레이너가 없습니다.");
      return;
    }
    for (Trainer t : trainers) {
      System.out.printf("  %d, %s\n", t.getNo(), t.getName());
    }

    int selectedTrainerNo = 0;
    loop: while (true) {
      try {
        selectedTrainerNo = Prompt.inputInt("변경할 트레이너 번호(취소: 0): ");
        if (selectedTrainerNo == 0) {
          System.out.println("기존 트레이너를 유지합니다.");
          break loop;
        }
        for (Trainer t : trainers) {
          if (t.getNo() == selectedTrainerNo) {
            break loop;
          }
        }
        System.out.println("유효하지 않은 트레이너 번호입니다.");

      } catch (Exception e) {
        System.out.println("숫자를 입력하세요.");
      }
    }

    if (selectedTrainerNo != 0) {
      pay.setTrainerNo(selectedTrainerNo);
    }

    pay.setRental(Prompt.inputInt(String.format(
        "운동복 대여(%s) (0: No | 1: Yes): ", Pay.getRentalLabel(pay.getRental()))));
    pay.setLocker(Prompt.inputInt(String.format(
        "개인 락커 대여(%s) (0: No | 1: Yes): ", Pay.getLockerLabel(pay.getLocker()))));
    pay.setPt(Prompt.inputInt(String.format(
        "PT 예약(%s) (0: No | 1: Yes): ", Pay.getPtLabel(pay.getPt()))));
    pay.setStartDate(Prompt.inputDate(String.format(
        "운동 시작일(%s): ", pay.getStartDate())));
    pay.setEndDate(Prompt.inputDate(String.format(
        "운동 종료일(%s): ", pay.getEndDate())));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("결제/예약 변경을 취소하였습니다.");
      return;
    }

    payDao.update(pay);

    System.out.println("결제/예약을 변경하였습니다.");
  }
}














