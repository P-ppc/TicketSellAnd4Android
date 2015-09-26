<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body >
<div style=" background:url(css/images/topback.gif) ;height: 99px" >
	<div style="padding:10px;text-align:right;">
		<span style="color:#ddd">欢迎你:</span>
		<label id="userName" style="color:#ddd">${sessionScope.worker.name}&nbsp;</label>
		<a href="#" style="color:#fff" onclick='window.location.href="clearSession.jsp"'>退出</a>
	</div>
	<div style="color:#fff;
	font-size:18px;
	font-weight:bold;
	padding:5px;">&nbsp;&nbsp;汽车售票系统</div>
</div>
</body>
</html>