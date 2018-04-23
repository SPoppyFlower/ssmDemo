package com.juan.controller;

import com.alibaba.fastjson.JSON;
import com.juan.pojo.SysUser;
import com.juan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by whj on 2018/01/30
 **/
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/doLogin")
    public String doLogin(Model model, HttpSession session, String userName, String password, String remember) throws Exception {
        //String validateCode = (String)session.getAttribute("validateCode");
        /*if(!validateCode.equals(randomCode)){
           throw  new CustomException("验证码错误！");
        }*/
        String msg;
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        if (null != remember) {
            token.setRememberMe(true);
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                session.setAttribute("userCode", userName);
                Date date = new Date();
                SysUser user = userService.getUserByShiro();
                SysUser us = new SysUser();
                us.setId(user.getId());
                us.setLastLoginTime(date);
                userService.updateByPrimaryKeySelective(us);

                return "welcome";
                //return "welcome";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定";
            model.addAttribute("message", msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用";
            model.addAttribute("message", msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期";
            model.addAttribute("message", msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在,请核对后重新登陆";
            model.addAttribute("message", msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
        }
        return "login";
    }


    //退出
    @RequestMapping(path = "/logOut")
    public String logOut(HttpServletRequest request){
        SysUser user = userService.getUserByShiro();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    //进入welcome页面
//    @RequestMapping(path = "/welcome")
//    public String welcome() {
//        return "index";
//    }

    //修改密码
    @RequestMapping(path = "/updatePW", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updatePW(String password, String newpassword) {
        String jsonString = JSON.toJSONString(false);
        SysUser user = userService.getUserByShiro();
        newpassword = new Md5Hash(newpassword).toString();
        password = new Md5Hash(password).toString();
        if (password.equals(user.getPassword())) {
            user.setPassword(newpassword);
            userService.updateByPrimaryKey(user);
            jsonString = JSON.toJSONString(true);
        }
        return jsonString;
    }
}