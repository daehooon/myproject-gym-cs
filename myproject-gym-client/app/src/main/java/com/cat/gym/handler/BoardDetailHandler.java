package com.cat.gym.handler;

import java.util.Iterator;
import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class BoardDetailHandler implements Command {

  Statement stmt;

  public BoardDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 보기]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");

    Iterator<String> results = stmt.executeQuery("board/select", Integer.toString(no));

    String[] fields = results.next().split(",");

    System.out.printf("제목: %s\n", fields[1]);
    System.out.printf("내용: %s\n", fields[2]);
    System.out.printf("작성자: %s\n", fields[3]);
    System.out.printf("작성일: %s\n", fields[4]);
    System.out.printf("조회수: %s\n", fields[5]);
    System.out.println();
  }

}
