package com.cat.gym.dao;

import java.util.List;
import com.cat.gym.domain.Pay;

public interface PayDao {

  int insert(Pay pay) throws Exception;

  List<Pay> findAll() throws Exception;

  List<Pay> findByTrainerNo(int trainerNo) throws Exception;

  Pay findByNo(int no) throws Exception;

  int update(Pay pay) throws Exception;

  int delete(int no) throws Exception;
}
