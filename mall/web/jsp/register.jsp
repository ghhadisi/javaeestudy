﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head></head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
    body {
        margin-top: 20px;
        margin: 0 auto;
    }

    .carousel-inner .item img {
        width: 100%;
        height: 300px;
    }

    .container .row div {
        /* position:relative;
        float:left; */
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }
</style>


<script type="text/javascript">
    $(function () {
      // $("#username").blur(function () {
      //
      // }).keyup(function () {
        $("#username").keyup(function () {
          // $(this).triggerHandler("blur")

          var strUsername = $("#username").val().trim()
          if (strUsername ==""){
              return
          }
          $.post("/userServlet",{
              "method":"checkUserNameCanUse",
              "username":strUsername
          },function (data,status) {
              // if (data==1){
              //
              // }
              console.log(data)
                if (data == "false"){
                    $("#username_span").html("<p style='color:red'> 名字不可以使用</p>")
                }else{
                    $("#username_span").html("")
                }

          })

      })
    })

    function  checkFormData() {

        var strUserName = $("#username").val().trim()
        console.log(strUserName)
        if (strUserName == ""){
            return false
        }

        var strPwd = $("#password").val().trim()
        var strConfirmpwd = $("#confirmpwd").val().trim()

        if (strConfirmpwd != strPwd){
            alert("密码不一致")
            return false
        }
        return true
    }
</script>
</head>
<body>

<%@ include file="/jsp/header.jsp" %>



<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
    <div class="row">

        <div class="col-md-2"></div>


        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <font>会员注册</font>USER REGISTER
            <form class="form-horizontal" style="margin-top:5px;" action="/userServlet" method="post" onsubmit="return checkFormData()">

                <input type="hidden" name="method" value="register">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
                    </div>
                    <span id="username_span"></span>

                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码"
                               name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="email" placeholder="Email" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="usercaption" placeholder="请输入姓名" name="name">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline">
                            <input type="radio"  id="inlineRadio1" value="男" name="sex"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio"  id="inlineRadio2" value="女" name="sex"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthday" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" id="birthday" name="birthday">
                    </div>
                </div>
                <div class="form-group">
                    <label for="myPhone" class="col-sm-2 control-label">手机</label>
                    <div class="col-sm-6">
                        <input id="myPhone" type="text" class="form-control" name="telephone">
                    </div>
                </div>
                <div class="form-group">
                    <label for="smscode" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="smscode" name="smscode">

                    </div>
                    <div class="col-sm-2">
                        <img src="${pageContext.request.contextPath}/img/captcha.jhtml"/>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" width="100" value="注册" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>


<%@include file="footer.jsp"%>


</body>
</html>




