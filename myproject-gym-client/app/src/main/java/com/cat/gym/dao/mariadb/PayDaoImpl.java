package com.cat.gym.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.cat.gym.dao.PayDao;
import com.cat.gym.domain.Pay;

public class PayDaoImpl implements PayDao {

  SqlSession sqlSession;

  public PayDaoImpl(SqlSession sqlSession) throws Exception {
    this.sqlSession = sqlSession;
  }

  @Override
  public int insert(Pay pay) throws Exception {
    return sqlSession.insert("PayMapper.insert", pay);
  }

  @Override
  public List<Pay> findAll() throws Exception {
    return sqlSession.selectList("PayMapper.findAll");
  }

  @Override
  public List<Pay> findByTrainerNo(int trainerNo) throws Exception {
    return sqlSession.selectList("PayMapper.findByTrainerNo", trainerNo);
  }

  @Override
  public Pay findByNo(int no) throws Exception {
    return sqlSession.selectOne("PayMapper.findByNo", no);
  }

  @Override
  public int update(Pay pay) throws Exception {
    return sqlSession.update("PayMapper.update", pay);
  }

  @Override
  public int delete(int no) throws Exception {
    return sqlSession.delete("PayMapper.delete", no);
  }

  @Override
  public int deleteByTrainerNo(int trainerNo) throws Exception {
    return sqlSession.delete("PayMapper.deleteByTrainerNo", trainerNo);
  }
}




















