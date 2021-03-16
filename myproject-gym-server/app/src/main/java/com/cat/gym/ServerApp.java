package com.cat.gym;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  int port;

  public static void main(String[] args) {
    ServerApp app = new ServerApp(2222);
    app.service();
  }

  public ServerApp(int port) {
    this.port = port;
  }

  public void service() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {

      System.out.println("서버 실행");
      processRequest(serverSocket.accept());

    } catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public void processRequest(Socket socket) {
    try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        String request = in.readUTF();
        System.out.println(request);

        out.writeUTF("확인");
        out.flush();

        if (request.equals("exit")) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("클라이언트의 요청을 처리하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
}
