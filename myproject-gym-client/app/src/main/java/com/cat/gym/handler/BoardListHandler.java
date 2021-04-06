package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.BoardDao;
import com.cat.gym.domain.Board;

public class BoardListHandler implements Command {

  BoardDao boardDao;

  public BoardListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 목록]");

    List<Board> boards = boardDao.findAll();

    for (Board b : boards) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          b.getNo(), 
          b.getTitle(), 
          b.getWriter().getName(),
          b.getRegisteredDate(),
          b.getViewCount());
    }
  }
}
