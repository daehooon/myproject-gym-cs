package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardDetailHandler implements Command {

  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 보기]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println();
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();
      return;
    }

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getId());
    System.out.printf("작성일: %s\n", board.getRegisteredDate());
    System.out.printf("조회수: %s\n", board.getViewCount());
    System.out.printf("Like: %s\n", board.getLike());
    System.out.println();
  }

}
