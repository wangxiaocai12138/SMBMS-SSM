package service;

import pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @param userCode
     * @param password
     * @return
     */
    User login( String userCode,String password);

    /**
     *查询用户
     * @param queryname
     * @param queryUserRole
     * @param userCode
     * @param uid
     * @param totalPageCount
     * @return
     */
    List<User> getUserList(String queryname, Integer queryUserRole, String userCode, Integer uid, Integer pageIndex);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 删除
     * @param uid
     * @return
     */
    boolean delUser(Integer uid);

    /**
     * 修改
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 查询总记录数
     * @return
     */
    Integer getUserCount();
}
