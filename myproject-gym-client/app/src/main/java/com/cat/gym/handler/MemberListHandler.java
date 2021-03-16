package com.cat.gym.handler;

import java.util.Iterator;
import java.util.List;
import com.cat.gym.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 목록]");
    System.out.println();

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%s %s %s\n", m.getName(), m.getId(), m.getPhoneNumber());
      System.out.println();
    }
  }

}
