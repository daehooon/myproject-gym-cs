package com.cat.gym.handler;

import com.cat.gym.dao.MemberDao;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberAddHandler implements Command {

  MemberDao memberDao;

  public MemberAddHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 등록]");

    Member m = new Member();
    m.setName(Prompt.inputString("이름: "));
    m.setEmail(Prompt.inputString("이메일: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setPhoto(Prompt.inputString("사진: "));
    m.setTel(Prompt.inputString("전화번호: "));

    memberDao.insert(m);

    System.out.println("Cat Gym 회원이 되신걸 환영합니다!");
  }
}
