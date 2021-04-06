package com.cat.gym.handler;

import java.text.SimpleDateFormat;
import com.cat.gym.dao.BoardDao;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardDetailHandler implements Command {

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  BoardDao boardDao;

  public BoardDetailHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 상세보기]");

    int no = Prompt.inputInt("글 번호: ");

    Board b = boardDao.findByNo(no);
    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", b.getTitle());
    System.out.printf("내용: %s\n", b.getContent());
    System.out.printf("작성자: %s\n", b.getWriter().getName());
    System.out.printf("등록일: %s\n", formatter.format(b.getRegisteredDate()));
    System.out.printf("조회수: %s\n", b.getViewCount());
    System.out.printf("좋아요: %s\n", b.getLike());

    boardDao.updateViewCount(no);
  }
}
