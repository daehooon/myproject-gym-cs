package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardAddHandler implements Command {

  Statement stmt;
  MemberValidator memberValidator;

  public BoardAddHandler(Statement stmt, MemberValidator memberValidator) {
    this.stmt = stmt;
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 등록]");
    System.out.println();

    Board b = new Board();
    b.setId(memberValidator.inputMember("아이디(취소: 빈 문자열): "));
    if (b.getId() == null) {
      System.out.println();
      System.out.println("게시글 등록을 취소합니다.");
      System.out.println();
      return;
    }
    b.setTitle(Prompt.inputString("제목: "));
    b.setContent(Prompt.inputString("내용: "));

    stmt.executeUpdate("board/insert",
        String.format("%s,%s,%s", b.getId(), b.getTitle(), b.getContent()));

    System.out.println();
    System.out.println("게시글을 등록하였습니다.");
    System.out.println();
  }

}
