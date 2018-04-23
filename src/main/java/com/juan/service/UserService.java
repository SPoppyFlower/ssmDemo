package com.juan.service;

import com.juan.pojo.SysPermission;
import com.juan.pojo.SysRole;
import com.juan.pojo.SysUser;

import java.util.List;

/**
 * Created by whj on 2018/04/20
 **/
public interface UserService {

    SysUser getUserByCode(String userCode);

    List<SysRole> getRoleListByUserId(Long userId);

    List<SysPermission> getPermissionListByUserId(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser getUserByShiro();

    int updateByPrimaryKey(SysUser record);
}