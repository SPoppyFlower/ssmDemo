package com.juan.shiro;

import com.juan.pojo.SysPermission;
import com.juan.pojo.SysRole;
import com.juan.pojo.SysUser;
import com.juan.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        long userId = user.getId();
        List<SysPermission> permissionList = userService.getPermissionListByUserId(userId);
        List<String> permissions = new ArrayList<String>();
        if (null != permissionList) {
            for (SysPermission permission : permissionList) {
                permissions.add(permission.getName());
                //System.out.println(permission.getName());
            }
        }
        List<SysRole> roleList = userService.getRoleListByUserId(userId);
        List<String> roles = new ArrayList<String>();
        if (null != roleList) {
            for (SysRole role : roleList) {
                roles.add(role.getName());
                //System.out.println(role.getName());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userCode = token.getUsername();
        SysUser user = userService.getUserByCode(userCode);
        if (null == user) {
            throw new UnknownAccountException();//没找到帐号
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }


}