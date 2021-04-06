package com.cat.gym.dao;

import java.util.List;
import com.cat.gym.domain.Member;
import com.cat.gym.domain.Trainer;

public interface TrainerDao {

  int insert(Trainer trainer) throws Exception;

  List<Trainer> findAll() throws Exception;

  Trainer findByNo(int no) throws Exception;

  int update(Trainer trainer) throws Exception;

  int delete(int no) throws Exception;

  int insertMember(int trainerNo, int memberNo) throws Exception;

  List<Member> findAllMembers(int trainerNo) throws Exception;

  int deleteMembers(int trainerNo) throws Exception;
}
