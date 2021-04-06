package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.dao.BoardDao;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardSearchHandler implements Command {

  BoardDao boardDao;

  public BoardSearchHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 검색]");

    String keyword = Prompt.inputString("검색어: ");

    if (keyword.length() == 0) {
      System.out.println("검색어를 입력하세요.");
      return;
    }

    List<Board> list = boardDao.findByKeyword(keyword);

    if (list.size() == 0) {
      System.out.println("검색어에 해당하는 게시글이 없습니다.");
      return;
    }

    for (Board b : list) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          b.getNo(), 
          b.getTitle(), 
          b.getWriter().getName(),
          b.getRegisteredDate(),
          b.getViewCount());
    }
  }
}
