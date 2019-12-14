package service.impl;

import mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Role;
import service.RoleService;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;


    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
