package com.cat.gym.handler;

import java.util.Iterator;
import com.cat.driver.Statement;
import com.cat.util.Prompt;

public class BoardSearchHandler implements Command {

  Statement stmt;

  public BoardSearchHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 검색]");
    System.out.println();

    String keyword = Prompt.inputString("검색어: ");

    if (keyword.length() == 0) {
      System.out.println();
      System.out.println("다시 입력하세요.");
      System.out.println();
      return;
    }

    Iterator<String> results = stmt.executeQuery("board/selectByKeyword", keyword);

    if (!results.hasNext()) {
      System.out.println();
      System.out.println("해당 검색어의 게시글이 없습니다.");
      System.out.println();
      return;
    }

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      System.out.printf("%s, %s, %s, %s, %s\n",
          fields[0],
          fields[1],
          fields[2],
          fields[3],
          fields[4]);
      System.out.println();
    }
  }
}
