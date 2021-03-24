package com.cat.gym.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.util.JsonFileHandler;
import com.cat.util.Request;
import com.cat.util.Response;

public class MemberTable implements DataTable {

  File jsonFile = new File("members.json");
  List<Member> list;

  public MemberTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Member.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Member member = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "member/insert":

        fields = request.getData().get(0).split(",");
        member = new Member();

        if (list.size() > 0) {
          member.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          member.setNo(1);
        }

        member.setName(fields[0]);
        member.setPhoneNumber(fields[1]);
        member.setResidence(fields[2]);
        member.setId(fields[3]);
        member.setPassword(fields[4]);
        member.setApply(new Date(System.currentTimeMillis()));

        list.add(member);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "member/selectall":
        for (Member m : list) {
          response.appendData(String.format("%d,%s,%s,%s",
              m.getNo(), m.getName(), m.getId(), m.getPhoneNumber()));
        }
        break;
      case "member/select":
        int no = Integer.parseInt(request.getData().get(0));

        member = getMember(no);
        if (member != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%s", 
              member.getNo(),
              member.getName(),
              member.getPhoneNumber(),
              member.getResidence(),
              member.getId(),
              member.getPassword(),
              member.getApply()));
        } else {
          throw new Exception("해당 번호의 회원이 없습니다.");
        }
        break;
      case "member/selectById":
        String id = request.getData().get(0);

        member = getMemberById(id);
        if (member != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%s", 
              member.getNo(),
              member.getName(),
              member.getPhoneNumber(),
              member.getResidence(),
              member.getId(),
              member.getPassword(),
              member.getApply()));
        } else {
          throw new Exception("해당 아이디의 회원이 없습니다.");
        }
        break;
      case "member/update":
        fields = request.getData().get(0).split(",");

        member = getMember(Integer.parseInt(fields[0]));
        if (member == null) {
          throw new Exception("해당 번호의 회원이 없습니다.");
        }

        member.setName(fields[1]);
        member.setPhoneNumber(fields[2]);
        member.setResidence(fields[3]);
        member.setId(fields[4]);
        member.setPassword(fields[5]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "member/delete":
        no = Integer.parseInt(request.getData().get(0));
        member = getMember(no);
        if (member == null) {
          throw new Exception("해당 번호의 회원이 없습니다.");
        }

        list.remove(member);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Member getMember(int memberNo) {
    for (Member m : list) {
      if (m.getNo() == memberNo) {
        return m;
      }
    }
    return null;
  }

  private Member getMemberById(String id) {
    for (Member m : list) {
      if (m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }
}
