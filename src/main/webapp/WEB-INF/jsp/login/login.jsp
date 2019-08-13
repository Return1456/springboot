<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>login.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
  </head>
  <script type="text/javascript">
  function jump(){
  window.location.href="register";
  }
  </script>
  <script type="text/javascript">
    function check(){
  var a=document.getElementById("username").value;
  var b=document.getElementById("password").value;
  if(a==""||b==""){
    document.getElementById("notice").innerHTML="empty input!";
  return false;
  }else{
  return true;
  }
  }
  </script>
  <body>
    <form action="usercheck" onsubmit="return check()" style="text-align:center" method="post">
        <br><br>    <br><br>
     <h1>Welcome!</h1>
    账号 <input id="username" name="username">
    <br><br>
    密码 <input id="password" name="password" type="password" width="100px">
    <br><br>
    <input type="submit" value="登陆">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button onClick="jump()">注册</button>
    <h3 id="notice" style="color:red"></h3>
    </form>
    
  </body>
</html>

