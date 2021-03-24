package com.cat.gym.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Trainer;
import com.cat.util.JsonFileHandler;
import com.cat.util.Request;
import com.cat.util.Response;

public class TrainerTable implements DataTable {

  File jsonFile = new File("trainers.json");
  List<Trainer> list;

  public TrainerTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Trainer.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Trainer trainer = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "trainer/insert":

        fields = request.getData().get(0).split(",");
        trainer = new Trainer();

        if (list.size() > 0) {
          trainer.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          trainer.setNo(1);
        }

        trainer.setBag(fields[0]);
        trainer.setPhoto(fields[1]);
        trainer.setName(fields[2]);
        trainer.setPhoneNumber(fields[3]);
        trainer.setContractS(Date.valueOf(fields[4]));
        trainer.setContractE(Date.valueOf(fields[5]));
        trainer.setMembers(fields[6]);

        list.add(trainer);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "trainer/selectall":
        for (Trainer p : list) {
          response.appendData(String.format("%s,%s,%s", 
              p.getNo(), p.getName(), p.getPhoneNumber()));
        }
        break;
      case "trainer/select":
        int no = Integer.parseInt(request.getData().get(0));

        trainer = getTrainer(no);
        if (trainer != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%s,%s", 
              trainer.getNo(),
              trainer.getBag(),
              trainer.getPhoto(),
              trainer.getName(),
              trainer.getPhoneNumber(),
              trainer.getContractS(),
              trainer.getContractE(),
              trainer.getMembers()));
        } else {
          throw new Exception("해당 번호의 트레이너가 없습니다.");
        }
        break;
      case "trainer/update":
        fields = request.getData().get(0).split(",");

        trainer = getTrainer(Integer.parseInt(fields[0]));
        if (trainer == null) {
          throw new Exception("해당 번호의 트레이너가 없습니다.");
        }

        trainer.setBag(fields[1]);
        trainer.setPhoto(fields[2]);
        trainer.setName(fields[3]);
        trainer.setPhoneNumber(fields[4]);
        trainer.setContractE(Date.valueOf(fields[5]));
        trainer.setMembers(fields[6]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "trainer/delete":
        no = Integer.parseInt(request.getData().get(0));
        trainer = getTrainer(no);
        if (trainer == null) {
          throw new Exception("해당 번호의 트레이너가 없습니다.");
        }

        list.remove(trainer);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Trainer getTrainer(int trainerNo) {
    for (Trainer t : list) {
      if (t.getNo() == trainerNo) {
        return t;
      }
    }
    return null;
  }
}
