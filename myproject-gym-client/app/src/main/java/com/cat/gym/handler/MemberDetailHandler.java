package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberDetailHandler extends AbstractMemberHandler {

  public MemberDetailHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 정보]");
    System.out.println();

    String id = Prompt.inputString("아이디: ");

    Member member = findById(id);
    if (member == null) {
      System.out.println();
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("전화번호: %s\n", member.getPhoneNumber());
    System.out.printf("주소: %s\n", member.getResidence());
    System.out.printf("비밀번호: %s\n", member.getPassword());
    System.out.printf("가입일: %s\n", member.getApply());
    System.out.println();
  }

}
