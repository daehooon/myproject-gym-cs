package com.cat.gym;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.cat.gym.handler.BoardAddHandler;
import com.cat.gym.handler.BoardDeleteHandler;
import com.cat.gym.handler.BoardDetailHandler;
import com.cat.gym.handler.BoardListHandler;
import com.cat.gym.handler.BoardSearchHandler;
import com.cat.gym.handler.BoardUpdateHandler;
import com.cat.gym.handler.Command;
import com.cat.util.Prompt;

public class ClientApp {

  ArrayDeque<String> commandStack = new ArrayDeque<>();
  LinkedList<String> commandQueue = new LinkedList<>();

  String serverAddress;
  int port;

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 2222);

    try {
      app.execute();

    } catch (Exception e) {
      System.out.println("클라이언트 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }

  public void execute() throws Exception {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddHandler());
    commandMap.put("/board/list", new BoardListHandler());
    commandMap.put("/board/detail", new BoardDetailHandler());
    commandMap.put("/board/update", new BoardUpdateHandler());
    commandMap.put("/board/delete", new BoardDeleteHandler());
    commandMap.put("/board/search", new BoardSearchHandler());

    //    commandMap.put("/member/add", new MemberAddHandler(stmt));
    //    commandMap.put("/member/list", new MemberListHandler(stmt));
    //    commandMap.put("/member/detail", new MemberDetailHandler(stmt));
    //    commandMap.put("/member/update", new MemberUpdateHandler(stmt));
    //    commandMap.put("/member/delete", new MemberDeleteHandler(stmt));
    //
    //    MemberValidator memberValidator = new MemberValidator(stmt);
    //
    //    commandMap.put("/pay/add", new PayAddHandler(stmt, memberValidator));
    //    commandMap.put("/pay/list", new PayListHandler(stmt));
    //    commandMap.put("/pay/detail", new PayDetailHandler(stmt));
    //    commandMap.put("/pay/update", new PayUpdateHandler(stmt));
    //    commandMap.put("/pay/delete", new PayDeleteHandler(stmt));
    //
    //    commandMap.put("/trainer/add", new TrainerAddHandler(stmt, memberValidator));
    //    commandMap.put("/trainer/list", new TrainerListHandler(stmt));
    //    commandMap.put("/trainer/detail", new TrainerDetailHandler(stmt));
    //    commandMap.put("/trainer/update", new TrainerUpdateHandler(stmt, memberValidator));
    //    commandMap.put("/trainer/delete", new TrainerDeleteHandler(stmt));

    try {
      while (true) {
        String command = Prompt.inputString(""
            + "=========================================================================\n"
            + "|                              ▶ Cat Gym ◀                              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [회원] = /member       [결제/예약] = /pay     [게시판] = /board      |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [트레이너] = /trainer  [최근 입력 기록] = history(or 2)              |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [세부 메뉴] */add list detail update delete search  EX) /member/add  |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "|  [프로그램 종료] = EXIT                                               |\n"
            + "|-----------------------------------------------------------------------|\n"
            + "\n명령어> ");

        if (command.length() == 0) {
          continue;
        }

        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command) {
            case "history":
              printCommandHistory(commandStack.iterator());
              break;
            case "history2":
              printCommandHistory(commandQueue.iterator());
              break;
            case "quit":
            case "exit":
              System.out.println("득근하세요!! Ten Reps!!!");
              return;
            default:
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령어입니다.");
              } else {
                commandHandler.service();
              }
          }

        } catch (Exception e) {
          System.out.println("---------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
          System.out.println("---------------------------------------------------------");
        }
        System.out.println();
      }

    } catch (Exception e) {
      System.out.println("서버와 통신 하는 중에 오류 발생!");
    }

    Prompt.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString("이어보기(enter) / 나가기(q): ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
