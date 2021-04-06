package com.cat.gym.dao.mariadb;

import java.sql.Connection;
import com.cat.gym.dao.TrainerDao;

public class TrainerDaoImpl implements TrainerDao {

  Connection con;

  public TrainerDaoImpl(Connection con) throws Exception {
    this.con = con;
  }

}
