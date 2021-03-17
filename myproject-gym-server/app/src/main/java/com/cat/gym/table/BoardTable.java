package com.cat.gym.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Board;
import com.cat.util.JsonFileHandler;
import com.cat.util.Request;
import com.cat.util.Response;

public class BoardTable implements DataTable {

  File jsonFile = new File("boards.json");
  List<Board> list;

  public BoardTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Board.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Board board = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "board/insert":
        fields = request.getData().get(0).split(",");

        board = new Board();
        if (list.size() > 0) {
          board.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          board.setNo(1);
        }

        board.setTitle(fields[0]);
        board.setId(fields[1]);
        board.setRegisteredDate(new Date(System.currentTimeMillis()));
        board.setContent(fields[2]);

        list.add(board);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "board/selectall":
        for (Board b : list) {
          response.appendData(String.format("%d,%s,%s,%s,%d", 
              b.getNo(),
              b.getTitle(),
              b.getId(),
              b.getRegisteredDate(),
              b.getViewCount()));
        }
        break;
      case "board/select":
        int no = Integer.parseInt(request.getData().get(0));

        board = getBoard(no);
        if (board != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%d", 
              board.getNo(),
              board.getTitle(),
              board.getContent(),
              board.getId(),
              board.getRegisteredDate(),
              board.getViewCount()));
        } else {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }
        break;
      case "board/selectByKeyword":
        String keyword = request.getData().get(0);

        for (Board b : list) {
          if (b.getTitle().contains(keyword) ||
              b.getContent().contains(keyword) ||
              b.getId().contains(keyword)) {

            response.appendData(String.format("%d,%s,%s,%s,%d", 
                b.getNo(),
                b.getTitle(),
                b.getId(),
                b.getRegisteredDate(),
                b.getViewCount()));
          }
        }
        break;
      case "board/update":
        fields = request.getData().get(0).split(",");

        board = getBoard(Integer.parseInt(fields[0]));
        if (board == null) {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }

        board.setTitle(fields[1]);
        board.setContent(fields[2]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "board/delete":
        no = Integer.parseInt(request.getData().get(0));
        board = getBoard(no);
        if (board == null) {
          throw new Exception("해당 번호의 게시글이 없습니다.");
        }

        list.remove(board);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Board getBoard(int boardNo) {
    for (Board b : list) {
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }
}
