package com.dz.l8023.project.service.imp;

import com.dz.l8023.project.service.UserService;
import com.dz.l8023.project.tab.dao.UserMapper;
import com.dz.l8023.project.tab.model.User;

import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userDao;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void insert() {
		User user = new User();
		user.setId(Integer.valueOf(1001));
		user.setName("age");
		this.userDao.insertSelective(user);
	}

	public void batchInsert() {
		SqlSession sqlSession = null;
		try {
			sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH);
			for (int i = 1; i < 1000; ++i) {
				User tempUser = new User();
				tempUser.setId(i);
				tempUser.setName("sdad" + i);
				sqlSession.getMapper(UserMapper.class).insert(tempUser);
				LogFactory.getLog(this.getClass()).info("asdaskjdlkajsdlasldjsalkdkl");
			}
			sqlSession.commit();
			LogFactory.getLog(this.getClass()).info("asdaskjdlkajsdlasldjsalkdkl");
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error(this, e);
			e.printStackTrace();
			System.out.println(e.getMessage());
			sqlSession.rollback();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		LogFactory.getLog(this.getClass()).info("asdaskjdlkajsdlasldjsalkdkl");
	}
}