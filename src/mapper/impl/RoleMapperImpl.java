package mapper.impl;

import mapper.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pojo.Role;

import java.util.List;
@Repository("roleMapper")
public class RoleMapperImpl implements RoleMapper {
    @Autowired
    @Qualifier("sqlSession")
    public SqlSession sqlSession;

    @Override
    public List<Role> getRoleList() {
        return sqlSession.getMapper(RoleMapper.class).getRoleList();
    }
}
