package mapper.impl;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pojo.User;

import java.util.List;


@Repository("userMapper")
public class UserMapperImpl implements UserMapper {
    @Autowired
    @Qualifier("sqlSession")
    public SqlSession sqlSession;


    @Override
    public User login(String userCode, String password) {
        return sqlSession.getMapper(UserMapper.class).login(userCode,password);
    }

    @Override
    public List<User> getUserList(String queryname, Integer queryUserRole, String userCode, Integer uid, Integer totalPageCount) {
        return sqlSession.getMapper(UserMapper.class).getUserList(queryname, queryUserRole, userCode, uid, totalPageCount);
    }

    @Override
    public Integer addUser(User user) {
        return sqlSession.getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public Integer delUser(Integer uid) {
        return sqlSession.getMapper(UserMapper.class).delUser(uid);
    }

    @Override
    public Integer updateUser(User user) {
        return sqlSession.getMapper(UserMapper.class).updateUser(user);
    }

    @Override
    public Integer getUserCount() {
        return sqlSession.getMapper(UserMapper.class).getUserCount();
    }
}
