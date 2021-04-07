package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.cat.gym.dao.PayDao;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Pay;
import com.cat.gym.domain.Trainer;
import com.cat.util.Prompt;

public class PayAddHandler implements Command {

  PayDao payDao;
  TrainerDao trainerDao;
  MemberValidator memberValidator;

  public PayAddHandler(PayDao payDao, TrainerDao trainerDao, MemberValidator memberValidator) {
    this.payDao = payDao;
    this.trainerDao = trainerDao;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[결제/예약 신청]");

    List<Trainer> trainers = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,name from gym_trainer order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      Pay p = new Pay();

      p.setOwner(memberValidator.inputMember("회원명(취소: 빈 문자열): "));
      if (p.getOwner() == null) {
        System.out.println("결제/예약을 취소합니다.");
        return;
      }

      p.setMembership(Prompt.inputInt(
          "회원권 선택\n"
              + "0: 1개월(80,000원)\n"
              + "1: 3개월(90,000원)\n"
              + "2: 6개월(150,000원)\n"
              + "3: 1년(240,000원)\n"
              + "> "));

      p.setNewMember(Prompt.inputInt(
          "신규회원 - 가입비 33,000원(0: No | 1: Yes): "));
      p.setRental(Prompt.inputInt(
          "운동복 대여 - 월 1만원(0: No | 1: Yes): "));
      p.setLocker(Prompt.inputInt(
          "개인 락커 대여 - 월 1만원(0: No | 1: Yes): "));
      p.setPt(Prompt.inputInt(
          "PT 예약 - [주2회/1시간] 월 336,000원(0: No | 1: Yes): "));

      while (rs.next()) {
        Trainer t = new Trainer();
        t.setNo(rs.getInt("no"));
        t.setName(rs.getString("name"));
        trainers.add(t);
      }

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
        String input = Prompt.inputString("희망하는 트레이너 번호(취소: 빈 문자열): ");
        if (input.length() == 0) {
          System.out.println("트레이너를 선택하지 않았습니다.");
          break loop;
        }
        try {
          selectedTrainerNo = Integer.parseInt(input);
        } catch (Exception e) {
          System.out.println("숫자를 입력하세요.");
          continue;
        }
        for (Trainer t : trainers) {
          if (t.getNo() == selectedTrainerNo) {
            break loop;
          }
        }
        System.out.println("유효하지 않은 트레이너 번호입니다.");
      }

      p.setStartDate(Prompt.inputDate("운동 시작일(yyyy-MM-dd): "));
      p.setEndDate(Prompt.inputDate("운동 종료일(yyyy-MM-dd): "));

      try (PreparedStatement stmt2 = con.prepareStatement(
          "insert into gym_pay(owner,mrs,nmr,rental,locker,pt,trainer_no,sdt,edt) values(?,?,?,?,?,?,?,?,?)")) {

        stmt2.setInt(1, p.getOwner().getNo());
        stmt2.setInt(2, p.getMembership());
        stmt2.setInt(3, p.getNewMember());
        stmt2.setInt(4, p.getRental());
        stmt2.setInt(5, p.getLocker());
        stmt2.setInt(6, p.getPt());
        stmt2.setInt(7, selectedTrainerNo);
        stmt2.setDate(8, p.getStartDate());
        stmt2.setDate(9, p.getEndDate());

        stmt2.executeUpdate();

        System.out.println("결제/예약이 완료되었습니다.");
      }
    }
  }
}













