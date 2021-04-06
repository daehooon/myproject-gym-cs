package com.cat.gym.handler;

import com.cat.gym.dao.BoardDao;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardUpdateHandler implements Command {

  BoardDao boardDao;

  public BoardUpdateHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 수정]");

    int no = Prompt.inputInt("글 번호: ");

    Board board = boardDao.findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    board.setTitle(Prompt.inputString(String.format("제목(%s): ", board.getTitle())));
    board.setContent(Prompt.inputString(String.format("내용(%s): ", board.getContent())));

    String input = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("게시글 수정을 취소하였습니다.");
      return;
    }

    boardDao.update(board);

    System.out.println("게시글을 수정하였습니다.");
  }
}
