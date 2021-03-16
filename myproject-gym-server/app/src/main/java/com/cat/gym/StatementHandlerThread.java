package com.cat.gym;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import com.cat.gym.table.DataTable;

public class StatementHandlerThread extends Thread {

  Socket socket;
  HashMap<String,DataTable> tableMap = new HashMap<>();

  public StatementHandlerThread(Socket socket, HashMap<String, DataTable> tableMap) {
    this.socket = socket;
    this.tableMap = tableMap;
  }

  @Override
  public void run() {

    try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

    } catch (Exception e) {
      System.out.println("클라이언트의 요청을 처리하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
}
