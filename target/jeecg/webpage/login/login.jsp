<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta charset="utf-8" />
  <title>CRRC智慧平台 登陆</title>
   <link rel="shortcut icon" href="images/favicon.ico">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="plug-in/ace/css/bootstrap.css" />
  <link rel="stylesheet" href="plug-in/ace/css/font-awesome.css" />

  <!-- ace styles -->
  <link rel="stylesheet" href="plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  <!--[if lte IE 9]>

  <!-- ace settings handler -->
  <script src="plug-in/ace/js/ace-extra.js"></script>

  <!--[if lte IE 8]>
  <script src="plug-in/ace/js/html5shiv.js"></script>
  <script src="plug-in/ace/js/respond.js"></script>
  <![endif]-->

</head>
<body class="login-layout light-login" >
<img src="plug-in/login/images/logo.png" style="max-width:200px;" width="100%">
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1">
        <div class="login-container">
          <div class="center">
            <%--<h1 id="id-text2" class="grey">--%>
              <%--<i class="ace-icon fa fa-leaf green"></i>--%>
               <%--系统名称--%>
            <%--</h1>--%>
              <%--<img src="logo.jpg"style="height: 200px;width: 400px;"/>--%>
              <%--<span>qwiejnc</span>--%>
          </div>
          <div class="space-6"></div>
          <div class="position-relative">
            <%--<div id="login-box" class="login-box visible widget-box no-border">--%>
              <div class="widget-body">
                <form id="loinForm" class="form-horizontal"   method="post">
                <!-- 单点登录参数 -->
                <input type="hidden" id="ReturnURL"  name="ReturnURL" value="${ReturnURL }"/>

                <div class="widget-main">
                 <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <div id="showErrMsg"></div>
				</div>
                  <h4 class="header ">
                    <%--<i class="ace-icon fa fa-coffee green"></i>--%>
                      <b>  用户登录  </b>
                  </h4>
                  <div class="space-6"></div>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right" style="border-radius: 5px 5px 5px 5px;border: 2px solid #fffefa">
									<input type="text"  name="userName" iscookie="true" class="form-control" placeholder="请输入用户名"  id="userName" value="admin"
                                           style="border:0 ; background-color:rgba(255,255,255,0.5) "/>
									<i class="ace-icon fa fa-user"></i>
								</span>
                      </label>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right" style="border-radius: 5px 5px 5px 5px;border: 2px solid #fffefa">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" value="123456"
                                    style="border:0;background-color:rgba(255,255,255,0.5) "/>
									<i class="ace-icon fa fa-lock"></i>
								</span>
                      </label>
                      <%--<label class="block clearfix">--%>
                        <%--<div class="input-group">--%>
                          <%--<input type="text" style="width:150px" name="randCode" class="form-control" placeholder="请输入验证码"  id="randCode"/>--%>
                          <%--<span class="input-group-addon" style="padding: 0px;"><img id="randCodeImage" src="randCodeImage"  /></span>--%>
                        <%--</div>--%>
                      <%--</label>--%>
                      <%--<div class="space"></div>--%>
                      <div class="clearfix" style="text-align: center;height: 22px">
                        <label class="inline" style="margin-top: 0px">
                          <input type="checkbox" class="ace" id="on_off"  name="remember" value="yes"/>
                          <span class="lbl">记住用户名</span>
                        </label>
                      </div>
                      <div style="text-align: center;height: 45px ;margin-top: 5px">
                        <button type="button" id="but_login"  onclick="checkUser()" class="width-35  btn btn-sm btn-primary"
                        style="border-radius: 8px; width: 130px;margin-top: 4px">
                          <i class="ace-icon fa fa-key"></i>
                          <span class="bigger-110" >登录</span>
                        </button>
                      </div>
                      <%--<div class="space-4"></div>--%>
                </div>

                </form>
              </div>
            <%--</div>--%>


              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/en.js"></script>
<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="webpage/login/login-ace.js"></script>
<%=lhgdialogTheme %>
<script type="text/javascript">
	$(function(){
		optErrMsg();
	});
	$("#errMsgContiner").hide();

  //  //输入验证码，回车登录
  // $(document).bind('keyup', function(event) {
	// 　　if (event.keyCode == "13") {
	// 　　　　$('#but_login').click();
	// 　　}
  // });

  //验证用户信息
  function checkUser(){
    if(!validForm()){
      return false;
    }
    newLogin();
  }

  /**
   * 刷新验证码
   */
  $('#randCodeImage').click(function(){
	    reloadRandCodeImage();
  });

</script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?098e6e84ab585bf0c2e6853604192b8b";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>

</body>
</html>