package com.cat.gym.dao;

import java.util.List;
import com.cat.gym.domain.Board;

public interface BoardDao {

  int insert(Board board) throws Exception;

  List<Board> findAll() throws Exception;

  Board findByNo(int no) throws Exception;

  int update(Board board) throws Exception;

  int updateViewCount(int no) throws Exception;

  int delete(int no) throws Exception;

  List<Board> findByKeyword(String keyword) throws Exception;
}
