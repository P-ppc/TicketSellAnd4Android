<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% if(request.getSession().getAttribute("worker")!=null){
	session.removeAttribute("worker");
   } 
   out.println("<script>window.parent.location.href='login.jsp';</script>");
%>
</head>
<body>

</body>
</html>