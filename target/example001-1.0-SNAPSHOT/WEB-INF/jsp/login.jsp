<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ include file="includes/top.jsp" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<style>

</style>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- 引用的css -->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <title>登录</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html,body {
            width: 100%;
            height: 100%;

        }
        nav{
            width: 100%;
            height: 50px;
            background-color: #00A2E8;
            text-align: center;
            line-height: 50px;
            color: white;
            font-size: 20px;
            font-weight: 700;
        }
        main{
            width: 100%;
            height: 100%;
            /*background-color: red;*/
            position: fixed;

            background-size:100% 100%;

        }
        .logn{
            width: 300px;
            height: 300px;
            background-color: #00A2E8;
            position: absolute;
            right: 20%;
            top: 20%;
        }
        .logn .pop{
            width: 100%;
            height: 80px;
            margin-top: 20px;
            margin-bottom: 20px;
            text-align: center;
        }
        .logn .ma{
            position: relative;
        }
        .logn .ma label{
            width: 60px;
            display: inline-block;
            height: 30px;
            line-height: 30px;
            margin-left: 20px;
            color: white;
        }
        .logn .ma input{
            border: 0;
            outline: 0;
            width: 200px;
            height: 30px;
            border-radius: 10px;
            margin-bottom: 20px;
            padding: 0 10px;
        }
        .logn .ma .warn {
            position: relative;
            left: 5%;
            bottom: 95px;
            /*color: red;*/
        }
    </style>
</head>
<body>
<nav>
    收衣网点管理系统

</nav>
<main>
    <div class="logn">
        <div class="pop">
            <%--<img src="/images/logn.png" alt="" style="width: 50px;height: 50px;"/><br/>--%>
            <span style="color: white;font-size: 18px;">用户登录</span>
        </div>
        <div class="ma">
            <form action="doLogin" name="loginForm" id="form" method="post">
                <label for="username">用户名:</label>
                <input type="text" id="userName" name="userName" placeholder="请输入用户名"/><br/>
                <label for="password">密码:</label>
                <input type="password" id="password" name="password" placeholder="请输入密码"/><br/>
                <a href="javascript:document.loginForm.submit();" style="width: 250px;color: white;margin-left: 25px;margin-top: 35px;">
                    登录</a>
                <%--class="width: 250px;color: white;margin-left: 25px;background-color: red;"/>--%>
            </form>
            <div class="warn" id="bt">
                <span><c:out value="${message}"/> </span>
            </div>
        </div>

    </div>
</main>
</body>
<script>
    sessionStorage.removeItem("username")

    $("#enter").click(function () {
        var usernames = $("#userName").val()
        sessionStorage.setItem("username",usernames)
    })

</script>
</html>
