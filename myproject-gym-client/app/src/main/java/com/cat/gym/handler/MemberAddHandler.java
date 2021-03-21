package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.gym.domain.Member;
import com.cat.util.Prompt;

public class MemberAddHandler implements Command {

  Statement stmt;

  public MemberAddHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[회원 등록]");
    System.out.println();

    Member m = new Member();
    m.setName(Prompt.inputString("이름: "));
    m.setPhoneNumber(Prompt.inputString("전화번호: "));
    m.setResidence(Prompt.inputString("주소: "));
    System.out.println("<아이디 및 비밀번호 입력시 대소문자를 구분하여 입력하세요.>");
    m.setId(Prompt.inputString("아이디: "));
    m.setPassword(Prompt.inputString("비밀번호: "));

    stmt.executeUpdate("member/insert",
        String.format("%s,%s,%s,%s,%s",
            m.getName(), m.getPhoneNumber(), m.getResidence(), m.getId(), m.getPassword()));

    System.out.println();
    System.out.println("Cat Gym 회원이 되신걸 환영합니다!");
    System.out.println();
  }
}
