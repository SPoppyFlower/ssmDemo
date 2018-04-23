package com.juan.dao;

import com.juan.pojo.SysRolePermissionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(SysRolePermissionKey key);

    int insert(SysRolePermissionKey record);

    int insertSelective(SysRolePermissionKey record);
}