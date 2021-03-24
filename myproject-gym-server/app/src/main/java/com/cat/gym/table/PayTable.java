package com.cat.gym.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Pay;
import com.cat.util.JsonFileHandler;
import com.cat.util.Request;
import com.cat.util.Response;

public class PayTable implements DataTable {

  File jsonFile = new File("pays.json");
  List<Pay> list;

  public PayTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Pay.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Pay pay = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "pay/insert":

        fields = request.getData().get(0).split(",");
        pay = new Pay();

        pay.setId(fields[0]);
        pay.setSelect(Integer.parseInt(fields[1]));
        pay.setJoin(fields[2]);
        pay.setRental(fields[3]);
        pay.setLocker(fields[4]);
        pay.setStartDate(Date.valueOf(fields[5]));

        list.add(pay);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "pay/selectall":
        for(Pay p : list) {
          response.appendData(String.format("%s,%s,%s", 
              p.getId(), p.getSelect(), p.getStartDate()));
        }
        break;
      case "pay/select":
        String id = request.getData().get(0);

        pay = getPay(id);
        if (pay != null) {
          response.appendData(String.format("%s,%s,%s,%s,%s,%s", 
              pay.getId(),
              pay.getSelect(),
              pay.getJoin(),
              pay.getRental(),
              pay.getLocker(),
              pay.getStartDate()));
        } else {
          throw new Exception("해당 아이디의 결제/예약 정보가 없습니다.");
        }
        break;
      case "pay/update":
        fields = request.getData().get(0).split(",");

        pay = getPay(fields[0]);
        if (pay == null) {
          throw new Exception("해당 아이디의 결제/예약 정보가 없습니다.");
        }

        pay.setRental(fields[1]);
        pay.setLocker(fields[2]);
        pay.setStartDate(Date.valueOf(fields[3]));

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "pay/delete":
        id = request.getData().get(0);
        pay = getPay(id);
        if (pay == null) {
          throw new Exception("해당 아이디의 결제/예약 정보가 없습니다.");
        }

        list.remove(pay);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Pay getPay(String memberId) {
    for (Pay p : list) {
      if (p.getId() == memberId) {
        return p;
      }
    }
    return null;
  }
}
