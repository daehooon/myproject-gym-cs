package com.cat.gym.handler;

import java.util.Iterator;
import java.util.List;
import com.cat.gym.domain.Board;

public class BoardListHandler implements Command {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 목록]");
    System.out.println();

    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board b = iterator.next();
      System.out.printf("%d %s %s %s %d\n",
          b.getNo(), b.getTitle(), b.getId(),
          b.getViewCount(), b.getLike());
      System.out.println();
    }
  }

}
