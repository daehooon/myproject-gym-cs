package com.cat.gym.handler;

import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardDeleteHandler implements Command {

  public BoardDeleteHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 삭제]");
    System.out.println();

    int no = Prompt.inputInt("글 번호: ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println();
      System.out.println("해당 번호의 글이 없습니다.");
      System.out.println();
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    System.out.println();

    if (input.equalsIgnoreCase("Y")) {
      boardList.remove(board);
      System.out.println("게시글을 삭제하였습니다.");
      System.out.println();

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
      System.out.println();
    }
  }

}
