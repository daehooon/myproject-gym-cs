package com.cat.gym.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.cat.gym.dao.TrainerDao;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;

public class TrainerDaoImpl implements TrainerDao {

  Connection con;

  public TrainerDaoImpl(SqlSession sqlSession) throws Exception {
    this.con = sqlSession;
  }

  @Override
  public int insert(Trainer trainer) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into gym_trainer(bag,photo,name,tel,cts,cte) values(?,?,?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      con.setAutoCommit(false);

      stmt.setString(1, trainer.getBag());
      stmt.setString(2, trainer.getPhoto());
      stmt.setString(3, trainer.getName());
      stmt.setString(4, trainer.getPhoneNumber());
      stmt.setDate(5, trainer.getContractS());
      stmt.setDate(6, trainer.getContractE());
      int count = stmt.executeUpdate();

      try (ResultSet keyRs = stmt.getGeneratedKeys()) {
        keyRs.next();
        trainer.setNo(keyRs.getInt(1));
      }

      for (Member member : trainer.getMembers()) {
        insertMember(trainer.getNo(), member.getNo());
      }

      con.commit();

      return count;

    } catch (Exception e) {
      con.rollback();

      throw e;

    } finally {
      con.setAutoCommit(true);
    }
  }

  @Override
  public List<Trainer> findAll() throws Exception {
    ArrayList<Trainer> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " no,"
            + " photo,"
            + " name,"
            + " tel,"
            + " cte,"
            + " members"
            + " from gym_trainer"
            + " order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Trainer trainer = new Trainer();
        trainer.setNo(rs.getInt("no"));
        trainer.setPhoto(rs.getString("photo"));
        trainer.setName(rs.getString("name"));
        trainer.setPhoneNumber(rs.getString("tel"));
        trainer.setContractE(rs.getDate("cte"));

        trainer.setMembers(findAllMembers(trainer.getNo()));

        list.add(trainer);
      }
    }
    return list;
  }

  @Override
  public Trainer findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from gym_trainer where no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Trainer trainer = new Trainer();
        trainer.setNo(rs.getInt("no"));
        trainer.setPhoto(rs.getString("photo"));
        trainer.setName(rs.getString("name"));
        trainer.setPhoneNumber(rs.getString("tel"));
        trainer.setContractS(rs.getDate("cts"));
        trainer.setContractE(rs.getDate("cte"));

        trainer.setMembers(findAllMembers(trainer.getNo()));

        return trainer;
      }
    }
  }

  @Override
  public int update(Trainer trainer) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update gym_trainer set"
            + " bag=?,photo=?,name=?,tel=?,cts=?,cte=?"
            + " where no=?")) {

      con.setAutoCommit(false);

      stmt.setString(1, trainer.getBag());
      stmt.setString(2, trainer.getPhoto());
      stmt.setString(3, trainer.getName());
      stmt.setString(4, trainer.getPhoneNumber());
      stmt.setDate(5, trainer.getContractS());
      stmt.setDate(6, trainer.getContractE());
      stmt.setInt(7, trainer.getNo());
      int count = stmt.executeUpdate();

      deleteMembers(trainer.getNo());

      for (Member member : trainer.getMembers()) {
        insertMember(trainer.getNo(), member.getNo());
      }

      con.commit();

      return count;

    } catch (Exception e) {
      con.rollback();

      throw e;

    } finally {
      con.setAutoCommit(true);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from gym_trainer where no=?")) {

      con.setAutoCommit(false);

      deleteMembers(no);

      stmt.setInt(1, no);
      int count = stmt.executeUpdate();
      con.commit();

      return count;

    } catch (Exception e) {
      con.rollback();

      throw e;

    } finally {
      con.setAutoCommit(true);
    }
  }

  @Override
  public int insertMember(int trainerNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into gym_member_trainer(member_no,trainer_no) values(?,?)")) {

      stmt.setInt(1, memberNo);
      stmt.setInt(2, trainerNo);
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAllMembers(int trainerNo) throws Exception {
    ArrayList<Member> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " m.no,"
            + " m.name"
            + " from gym_member_trainer mt"
            + " inner join gym_member m on mt.member_no=m.no"
            + " where mt.trainer_no=?")) {

      stmt.setInt(1, trainerNo);

      try (ResultSet memberRs = stmt.executeQuery()) {
        while (memberRs.next()) {
          Member m = new Member();
          m.setNo(memberRs.getInt("no"));
          m.setName(memberRs.getString("name"));
          list.add(m);
        }
      }
    }

    return list;
  }

  @Override
  public int deleteMembers(int trainerNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from gym_member_trainer where trainer_no=?")) {
      stmt.setInt(1, trainerNo);
      return stmt.executeUpdate();
    }
  }
}
