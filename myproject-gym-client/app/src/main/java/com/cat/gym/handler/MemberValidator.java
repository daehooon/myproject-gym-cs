package com.cat.gym.handler;

import java.util.ArrayList;
import java.util.List;
import com.cat.gym.dao.MemberDao;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberValidator {

  MemberDao memberDao;

  public MemberValidator(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public Member inputMember(String promptTitle) throws Exception {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } 

      Member m = memberDao.findByName(name);
      if (m != null) {
        return m;
      }
      System.out.println("등록되지 않은 회원입니다.");
    }
  }

  public List<Member> inputMembers(String promptTitle) throws Exception {
    ArrayList<Member> members = new ArrayList<>();

    while (true) {
      Member member = inputMember(promptTitle);
      if (member == null) {
        return members;
      } else {
        members.add(member);
      }
    }
  }

}
