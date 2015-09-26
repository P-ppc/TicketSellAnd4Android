<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.8.0.js"></script>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="js/ticketReturn.js?time="></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退票管理</title>
<%
    if(request.getSession().getAttribute("worker") == null) {
		response.sendRedirect("clearSession.jsp");
    }
%>
</head>
<body>
   <div id="ticketReturnDg"></div>
   
   <div id="ticketReturnTb">
     <br>
     <table>
       <tr>
        <td>&nbsp;售票单号：<input type="text" id="orderNumQuery" style="width:90px"/>&nbsp;</td>
        <td><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a></td>
       </tr>
     </table>
   </div>
</body>
</html>