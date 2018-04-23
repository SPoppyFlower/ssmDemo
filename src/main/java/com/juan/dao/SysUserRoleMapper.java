package com.juan.dao;

import com.juan.pojo.SysRole;
import com.juan.pojo.SysUserRoleKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);

    List<SysRole> selectRoleListByUserId(Long userId);
}