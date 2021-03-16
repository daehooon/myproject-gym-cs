package com.cat.gym;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.cat.util.Prompt;

public class ClientApp {

  String serverAddress;
  int port;

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 2222);
    app.execute();
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }

  public void execute() {
    try (Socket socket = new Socket(this.serverAddress, this.port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        String message = Prompt.inputString("입력> ");

        out.writeUTF(message);
        out.flush();

        String response = in.readUTF();
        System.out.println(response);

        if (message.equals("exit")) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("서버와 통신하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
}