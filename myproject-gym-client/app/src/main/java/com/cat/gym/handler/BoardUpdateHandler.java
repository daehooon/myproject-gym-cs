package com.cat.gym.handler;

import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class BoardUpdateHandler implements Command {

  Statement stmt;

  public BoardUpdateHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 수정]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");

    String[] fields = stmt.executeQuery("board/select", Integer.toString(no)).next().split(",");

    String title = Prompt.inputString(String.format("제목(%s): ", fields[1]));
    String content = Prompt.inputString(String.format("내용(%s): ", fields[2]));

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    System.out.println();

    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("게시글 수정을 취소하였습니다.");
      System.out.println();
      return;
    }

    stmt.executeUpdate("board/update", String.format("%d,%s,%s", no, title, content));

    System.out.println("게시글을 수정하였습니다.");
    System.out.println();
  }
}
