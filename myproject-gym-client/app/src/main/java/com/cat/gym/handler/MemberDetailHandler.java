package com.cat.gym.handler;

import com.cat.gym.dao.MemberDao;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberDetailHandler implements Command {

  MemberDao memberDao;

  public MemberDetailHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service() throws Exception{
    System.out.println("[회원 정보]");

    int no = Prompt.inputInt("회원번호: ");

    Member m = memberDao.findByNo(no);

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("이메일: %s\n", m.getEmail());
    System.out.printf("사진: %s\n", m.getPhoto());
    System.out.printf("전화번호: %s\n", m.getTel());
    System.out.printf("가입일: %s\n", m.getRegisteredDate());
  }
}
