package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 정보 변경]");
    System.out.println();

    String idC = Prompt.inputString("아이디 확인: ");
    System.out.println();

    Member member = findById(idC);
    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      System.out.println();
      return;
    }

    while(true) {
      String name = Prompt.inputString(String.format("이름(%s): ", member.getName()));
      String phoneNumber = Prompt.inputString(String.format("전화번호(%s): ", member.getPhoneNumber()));
      String residence = Prompt.inputString(String.format("주소(%s): ", member.getResidence()));
      String id = Prompt.inputString(String.format("아이디(%s): ", member.getId()));
      String password = Prompt.inputString(String.format("비밀번호(%s): ", member.getPassword()));

      if (name.equals("") ||
          phoneNumber.equals("") ||
          residence.equals("") ||
          id.equals("") ||
          password.equals("")) {
        System.out.println();
        System.out.println("모든 항목에 정보를 입력해 주세요.");
        System.out.println();
        continue;
      } else {
        System.out.println();
        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
        System.out.println();

        if (input.equalsIgnoreCase("Y")) {
          member.setName(name);
          member.setPhoneNumber(phoneNumber);
          member.setResidence(residence);
          member.setId(id);
          member.setPassword(password);
          System.out.println("정보를 변경하였습니다.");
          System.out.println();

        } else {
          System.out.println("정보 변경을 취소하였습니다.");
          System.out.println();
        }
      }
      break;
    }
  }

}
