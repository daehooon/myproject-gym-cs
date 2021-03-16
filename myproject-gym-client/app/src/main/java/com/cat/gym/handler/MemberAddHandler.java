package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 등록]");
    System.out.println();

    Member m = new Member();

    while (true) {
      m.setName(Prompt.inputString("이름: "));
      m.setPhoneNumber(Prompt.inputString("전화번호: "));
      m.setResidence(Prompt.inputString("주소: "));
      System.out.println("<아이디 및 비밀번호 입력시 대소문자를 구분하여 입력하세요.>");
      m.setId(Prompt.inputString("아이디: "));
      m.setPassword(Prompt.inputString("비밀번호: "));
      m.setApply(new java.sql.Date(System.currentTimeMillis()));

      if (m.getName().equals("") || 
          m.getPhoneNumber().equals("") || 
          m.getResidence().equals("") || 
          m.getId().equals("") || 
          m.getPassword().equals("")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력해 주세요.");
        System.out.println();
        continue;
      } else {
        memberList.add(m);
        break;
      }
    }
    System.out.println();
    System.out.println("Cat Gym 회원이 되신걸 환영합니다!");
    System.out.println();
  }

}
