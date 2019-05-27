<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>register.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
     <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<script type="text/javascript">

$(document).ready(function() {
$("#username").keydown(function(){
document.getElementById("notice").innerHTML="";
});
$("#username").change(function() {
var user=document.getElementById("username").value;
$.get("registercheck?username="+user,function(data){
document.getElementById("notice").innerHTML=data;
if(data=="Account available"){
$("#notice").css("color","green");
$("#sub").removeAttr("disabled");
}else if(data=="Account registered"){
$("#notice").css("color","red");
$("#sub").attr("disabled",true);
}
});
});
});





</script>
  </head>
  <script type="text/javascript">
  function check(){
  var user=document.getElementById("username").value;
  var psw=document.getElementById("password").value;
  if(user!=""&&psw!=""){
  return true;}
  else{
  document.getElementById("notice").innerHTML="empty input!";
	$("#notice").css("color","red");

   setTimeout(function(){
  document.getElementById("notice").innerHTML="提示：注册成功—>登陆界面";
  $("#notice").css("color","#000000");
  },1000);
  return false;
  }

  
  }
  
  
  </script>
  <body>
    <form action="doregister" style="text-align:center" Onsubmit="return check()" method="post" >
    <br><br><br><br>
    <h1>注册</h1>

    账号 <input id="username" name="username" width="100px">
    <br><br>
    密码 <input id="password" type="password" name="password" width="100px">
   <br><br>
   <input id="sub" type="submit" value="注册">
        <h3 id="notice">提示：注册成功》登陆界面</h3>
    
    </form>
  </body>
</html>

