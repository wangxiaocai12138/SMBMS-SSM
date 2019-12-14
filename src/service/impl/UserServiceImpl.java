package service.impl;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pojo.User;
import service.UserService;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("userMapper")
    public UserMapper userMapper;
    @Override
    public User login(String userCode, String password) {
        return userMapper.login(userCode,password);
    }

    @Override
    public List<User> getUserList(String queryname, Integer queryUserRole, String userCode, Integer uid, Integer pageIndex) {
        return userMapper.getUserList(queryname,queryUserRole,userCode,uid,pageIndex);
    }

    @Override
    public boolean addUser(User user) {
        if(userMapper.addUser(user)>0)
            return true;
        return false;
    }

    @Override
    public boolean delUser(Integer uid) {
        if(userMapper.delUser(uid)>0)
            return true;
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if(userMapper.updateUser(user)>0)
            return true;
        return false;
    }

    @Override
    public Integer getUserCount() {
        return userMapper.getUserCount();
    }

}
