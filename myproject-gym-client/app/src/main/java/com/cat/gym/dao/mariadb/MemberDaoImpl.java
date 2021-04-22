package com.cat.gym.dao.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.cat.gym.dao.MemberDao;
import com.cat.gym.domain.Member;

public class MemberDaoImpl implements MemberDao {

  SqlSession sqlSession;

  public MemberDaoImpl(SqlSession sqlSession) throws Exception {
    this.sqlSession = sqlSession;
  }

  @Override
  public int insert(Member member) throws Exception {
    return sqlSession.insert
  }

  @Override
  public List<Member> findAll() throws Exception {
    ArrayList<Member> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select no,name,email,photo,tel from gym_member order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("no"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setPhoto(rs.getString("photo"));
        m.setTel(rs.getString("tel"));

        list.add(m);
      }
    }

    return list;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from gym_member where no = ?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Member m = new Member();
        m.setNo(no);
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setPhoto(rs.getString("photo"));
        m.setTel(rs.getString("tel"));
        m.setRegisteredDate(rs.getDate("cdt"));

        return m;
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update gym_member set name=?,email=?,password=password(?),photo=?,tel=? where no=?")) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoto());
      stmt.setString(5, member.getTel());
      stmt.setInt(6, member.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from gym_member where no=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  @Override
  public Member findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from gym_member where name=?")) {

      stmt.setString(1, name);

      ResultSet rs = stmt.executeQuery();

      if (!rs.next()) {
        return null;
      }

      Member m = new Member();
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setEmail(rs.getString("email"));
      m.setPhoto(rs.getString("photo"));
      m.setTel(rs.getString("tel"));
      m.setRegisteredDate(rs.getDate("cdt"));

      return m;
    }
  }
}
