package com.cat.gym.dao;

import java.util.List;
import com.cat.gym.domain.Member;

public interface MemberDao {

  int insert(Member member) throws Exception;

  List<Member> findAll() throws Exception;

  Member findByNo(int no) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  Member findByName(String name) throws Exception;
}
