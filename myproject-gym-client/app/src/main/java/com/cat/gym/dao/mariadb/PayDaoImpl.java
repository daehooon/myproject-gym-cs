package com.cat.gym.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.cat.gym.dao.PayDao;
import com.cat.gym.domain.Pay;

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

}
