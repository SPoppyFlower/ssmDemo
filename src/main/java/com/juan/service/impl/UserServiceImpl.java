package com.juan.service.impl;

import com.juan.dao.*;
import com.juan.pojo.SysPermission;
import com.juan.pojo.SysRole;
import com.juan.pojo.SysUser;
import com.juan.pojo.SysUserRoleKey;
import com.juan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by whj on 2018/04/20
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysPermissionMapper permissionMapper;

    public SysUser getUserByCode(String userCode) {
        return userMapper.selectUserByCode(userCode);
    }

    public List<SysRole> getRoleListByUserId(Long userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }

    public List<SysPermission> getPermissionListByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }

    public int updateByPrimaryKeySelective(SysUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public SysUser getUserByShiro() {
        Subject currentUser = SecurityUtils.getSubject();//获取当前用户
        SysUser user = (SysUser) currentUser.getPrincipal();
        return user;
    }

    public int updateByPrimaryKey(SysUser record) {
        return userMapper.updateByPrimaryKey(record);
    }
}