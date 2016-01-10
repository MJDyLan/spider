<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h1>欢迎您使用木子科技提供的抓取接口</h1>
<form action="/account/doLogin" method="post">
   宜人贷账号:<input type="text" value="${account }" name="account">
   宜人贷密码:<input type="text" value="${password }" name="password">
  <input type="submit" value="登录">
</form>
</body>
</html>