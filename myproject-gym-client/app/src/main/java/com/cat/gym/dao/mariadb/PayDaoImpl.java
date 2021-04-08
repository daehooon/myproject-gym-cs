package com.cat.gym.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.cat.gym.dao.PayDao;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Pay;
import jdk.internal.org.jline.utils.ShutdownHooks.Task;

public class PayDaoImpl implements PayDao {

  Connection con;

  public PayDaoImpl(Connection con) throws Exception {
    this.con = con;
  }

  @Override
  public int insert(Pay pay) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert"
            + " into gym_pay(owner,mrs,nmr,rental,locker,pt,trainer_no,sdt,edt)"
            + " values(?,?,?,?,?,?,?,?,?)")) {

      stmt.setInt(1, pay.getOwner().getNo());
      stmt.setInt(2, pay.getMembership());
      stmt.setInt(3, pay.getNewMember());
      stmt.setInt(4, pay.getRental());
      stmt.setInt(5, pay.getLocker());
      stmt.setInt(6, pay.getPt());
      stmt.setInt(7, pay.getTrainerNo());
      stmt.setDate(8, pay.getStartDate());
      stmt.setDate(9, pay.getEndDate());
      return stmt.executeUpdate();
    }
  }




  @Override
  public List<Pay> findAll() throws Exception {
    return findAll(0);
  }

  @Override
  public List<Pay> findByProjectNo(int trainerNo) throws Exception {
    return findAll(trainerNo);
  }



  private List<Pay> findAll(int trainerNo) throws Exception {
    ArrayList<Pay> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + "  p.no,"
            + "  p.mrs,"
            + "  p.pt,"
            + "  p.sdt,"
            + "  p.edt,"
            + "  m.no as owner_no,"
            + "  m.name as owner_name,"
            + "  t.no as trainer_no,"
            + "  t.name as trainer_name"
            + " from gym_pay p"
            + "  inner join gym_member m on p.owner=m.no"
            + "  inner join gym_trainer t on p.trainer_no=t.no"
            + " where p.trainer_no=? or 0=?"
            + " order by p.owner asc")) {

      stmt.setInt(1, trainerNo);
      stmt.setInt(2, trainerNo);

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        Pay pay = new Pay();
        pay.setNo(rs.getInt("no"));

        Member owner = new Member();
        owner.setNo(rs.getInt("owner_no"));
        owner.setName(rs.getString("owner_name"));
        pay.setOwner(owner);

        pay.setMembership(rs.getInt("mrs"));
        pay.setPt(rs.getInt("pt"));

        pay.setTrainerNo(rs.getInt("trainer_no"));
        pay.setTrainerName(rs.getString("trainer_name"));

        pay.setStartDate(rs.getDate("sdt"));
        pay.setEndDate(rs.getDate("edt"));

        list.add(pay);
      }
      return list;
    }
  }





  @Override
  public Task findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select "
            + "   t.no,"
            + "   t.content,"
            + "   t.deadline,"
            + "   t.status,"
            + "   m.no as owner_no,"
            + "   m.name as owner_name,"
            + "   p.no as project_no,"
            + "   p.title as project_title"
            + " from pms_task t "
            + "   inner join pms_member m on t.owner=m.no"
            + "   inner join pms_project p on t.project_no=p.no"
            + " where t.no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Task task = new Task();
        task.setNo(rs.getInt("no"));
        task.setContent(rs.getString("content"));
        task.setDeadline(rs.getDate("deadline"));
        task.setStatus(rs.getInt("status"));

        Member owner = new Member();
        owner.setNo(rs.getInt("owner_no"));
        owner.setName(rs.getString("owner_name"));
        task.setOwner(owner);

        task.setProjectNo(rs.getInt("project_no"));
        task.setProjectTitle(rs.getString("project_title"));

        return task;
      }
    }
  }

  @Override
  public int update(Task task) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_task set content=?,deadline=?,owner=?,status=?,project_no=? where no=?")) {

      stmt.setString(1, task.getContent());
      stmt.setDate(2, task.getDeadline());
      stmt.setInt(3, task.getOwner().getNo());
      stmt.setInt(4, task.getStatus());
      stmt.setInt(5, task.getProjectNo());
      stmt.setInt(6, task.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_task where no=?")) {

      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }
}



















