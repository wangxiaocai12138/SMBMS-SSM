package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * 登录
     * @param userCode
     * @param password
     * @return
     */
    User login(@Param("userCode") String userCode,
                      @Param("password") String password);

    /**
     * 用户管理首页
     * @return
     * @param queryname
     * @param queryUserRole
     * @param userCode
     * @param uid
     * @param totalPageCount
     */
    List<User> getUserList(@Param("userName") String queryname,
                           @Param("userRole") Integer queryUserRole,
                           @Param("userCode") String userCode, @Param("uid") Integer uid,
                           @Param("pageIndex") Integer pageIndex);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 删除
     * @param uid
     * @return
     */
    Integer delUser(@Param("uid") Integer uid);

    /**
     * 添加
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 查询总记录数
     * @return
     */
    Integer getUserCount();
}
