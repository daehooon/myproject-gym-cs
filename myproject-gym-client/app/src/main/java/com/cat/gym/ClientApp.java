package com.cat.gym;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.cat.gym.dao.BoardDao;
import com.cat.gym.dao.MemberDao;
import com.cat.gym.dao.PayDao;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.dao.mariadb.BoardDaoImpl;
import com.cat.gym.dao.mariadb.MemberDaoImpl;
import com.cat.gym.dao.mariadb.PayDaoImpl;
import com.cat.gym.dao.mariadb.TrainerDaoImpl;
import com.cat.gym.handler.BoardAddHandler;
import com.cat.gym.handler.BoardDeleteHandler;
import com.cat.gym.handler.BoardDetailHandler;
import com.cat.gym.handler.BoardListHandler;
import com.cat.gym.handler.BoardSearchHandler;
import com.cat.gym.handler.BoardUpdateHandler;
import com.cat.gym.handler.Command;
import com.cat.gym.handler.MemberAddHandler;
import com.cat.gym.handler.MemberDeleteHandler;
import com.cat.gym.handler.MemberDetailHandler;
import com.cat.gym.handler.MemberListHandler;
import com.cat.gym.handler.MemberUpdateHandler;
import com.cat.gym.handler.MemberValidator;
import com.cat.gym.handler.PayAddHandler;
import com.cat.gym.handler.PayDeleteHandler;
import com.cat.gym.handler.PayDetailHandler;
import com.cat.gym.handler.PayListHandler;
import com.cat.gym.handler.PayUpdateHandler;
import com.cat.gym.handler.TrainerAddHandler;
import com.cat.gym.handler.TrainerDeleteHandler;
import com.cat.gym.handler.TrainerDetailHandler;
import com.cat.gym.handler.TrainerListHandler;
import com.cat.gym.handler.TrainerUpdateHandler;
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

    InputStream mybatisConfigStream = Resources.getResourceAsStream(
        "con/cat/gym/conf/mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigStream);

    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    BoardDao boardDao = new BoardDaoImpl(sqlSession);
    MemberDao memberDao = new MemberDaoImpl(sqlSession);
    PayDao payDao = new PayDaoImpl(sqlSession);
    TrainerDao trainerDao = new TrainerDaoImpl(sqlSession);

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddHandler(boardDao));
    commandMap.put("/board/list", new BoardListHandler(boardDao));
    commandMap.put("/board/detail", new BoardDetailHandler(boardDao));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao));
    commandMap.put("/board/search", new BoardSearchHandler(boardDao));

    commandMap.put("/member/add", new MemberAddHandler(memberDao));
    commandMap.put("/member/list", new MemberListHandler(memberDao));
    commandMap.put("/member/detail", new MemberDetailHandler(memberDao));
    commandMap.put("/member/update", new MemberUpdateHandler(memberDao));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberDao));

    MemberValidator memberValidator = new MemberValidator(memberDao);

    commandMap.put("/pay/add", new PayAddHandler(payDao, trainerDao, memberValidator));
    commandMap.put("/pay/list", new PayListHandler(payDao));
    commandMap.put("/pay/detail", new PayDetailHandler(payDao));
    commandMap.put("/pay/update", new PayUpdateHandler(payDao, trainerDao));
    commandMap.put("/pay/delete", new PayDeleteHandler(payDao));

    commandMap.put("/trainer/add", new TrainerAddHandler(trainerDao, memberValidator));
    commandMap.put("/trainer/list", new TrainerListHandler(trainerDao));
    commandMap.put("/trainer/detail", new TrainerDetailHandler(trainerDao));
    commandMap.put("/trainer/update", new TrainerUpdateHandler(trainerDao, memberValidator));
    commandMap.put("/trainer/delete", new TrainerDeleteHandler(trainerDao));

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
            + "|  [세부 메뉴] .../add list detail update delete (/board/search)        |\n"
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

    sqlSession.close();
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
