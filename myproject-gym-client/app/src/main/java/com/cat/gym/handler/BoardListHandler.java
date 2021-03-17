package com.cat.gym.handler;

import java.util.Iterator;
import com.cat.driver.Statement;

public class BoardListHandler implements Command {

  Statement stmt;

  public BoardListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 목록]");
    System.out.println();

    Iterator<String> results = stmt.executeQuery("board/selectall");

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
