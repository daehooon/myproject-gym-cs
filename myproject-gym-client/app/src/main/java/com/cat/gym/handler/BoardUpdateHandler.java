package com.cat.gym.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cat.gym.domain.Board;
import com.cat.util.Prompt;

public class BoardUpdateHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("[게시글 수정]");

    int no = Prompt.inputInt("글 번호: ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,title,content from gym_board where no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update gym_board set title=?, content=? where no=?")) {

      Board board = new Board();

      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        board.setNo(no); 
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
      }

      board.setTitle(Prompt.inputString(String.format("제목(%s)? ", board.getTitle())));
      board.setContent(Prompt.inputString(String.format("내용(%s)? ", board.getContent())));

      String input = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if (!input.equalsIgnoreCase("Y")) {
        System.out.println("게시글 수정을 취소하였습니다.");
        return;
      }

      stmt2.setString(1, board.getTitle());
      stmt2.setString(2, board.getContent());
      stmt2.setInt(3, board.getNo());
      stmt2.executeUpdate();

      System.out.println("게시글을 수정하였습니다.");
    }
  }
}
