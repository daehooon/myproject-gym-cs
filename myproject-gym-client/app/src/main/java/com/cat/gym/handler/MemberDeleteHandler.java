package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberDeleteHandler extends AbstractMemberHandler {

  public MemberDeleteHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 탈퇴]");
    System.out.println();

    String id = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Member member = findById(id);
    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      memberList.remove(member);
      System.out.println("회원을 탈퇴하였습니다.");
      System.out.println();

    } else {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      System.out.println();
    }
  }

}
