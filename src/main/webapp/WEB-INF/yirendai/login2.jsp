<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h1>(宜人贷web)</h1>
<form action="/spider/account/doWebLogin" method="post">
   宜人贷账号:<input type="text" value="" name="username"><br>
   宜人贷密码:<input type="text" value="" name="password"><br>
   验证码：<input type="text" value="" name="authcode"><img alt="" src="${authImage }">
  <input type="submit" value="登录">
</form>
</body>
</html>