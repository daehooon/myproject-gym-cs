package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class MemberValidator {

  Statement stmt;

  public MemberValidator(Statement stmt) {
    this.stmt = stmt;
  }

  public String inputMember(String promptTitle) {
    while (true) {
      String id = Prompt.inputString(promptTitle);
      if (id.length() == 0) {
        return null;
      }

      try {
        return this.stmt.executeQuery("member/selectById", id).next().split(",")[1];
      } catch (Exception e) {
        System.out.println("등록된 회원이 아닙니다.");
        System.out.println();
      }
    }
  }

  public String inputMembers(String promptTitle) {
    String members = "";
    while (true) {
      String name = inputMember(promptTitle);
      if (name == null) {
        return members;
      } else {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += name;
      }
    }
  }

}
