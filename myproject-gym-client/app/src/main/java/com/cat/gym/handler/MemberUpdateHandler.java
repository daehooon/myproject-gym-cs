package com.cat.gym.handler;

import com.cat.gym.dao.MemberDao;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberUpdateHandler implements Command {

  MemberDao memberDao;

  public MemberUpdateHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 정보 변경]");

    int no = Prompt.inputInt("회원번호: ");

    Member member = memberDao.findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    member.setName(Prompt.inputString(String.format("이름(%s): ", member.getName())));
    member.setEmail(Prompt.inputString(String.format("이메일(%s): ", member.getEmail())));
    member.setPassword(Prompt.inputString("새 암호: "));
    member.setPhoto(Prompt.inputString(String.format("사진(%s): ", member.getPhoto())));
    member.setTel(Prompt.inputString(String.format("전화(%s): ", member.getTel())));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("회원 정보 변경을 취소하였습니다.");
      return;
    }

    memberDao.update(member);

    System.out.println("회원 정보를 변경하였습니다.");
  }
}
