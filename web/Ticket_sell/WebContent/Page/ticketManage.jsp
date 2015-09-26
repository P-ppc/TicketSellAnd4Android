<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--引入包名  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="js/jquery-1.8.0.js"></script>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="js/ticketManage.js?time="></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车票管理</title>
<%
    if(request.getSession().getAttribute("worker") == null) {
		response.sendRedirect("clearSession.jsp");
    }
%>
</head>
<body >
<!-- toolbar -->
<div id="tb">
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog_add()">添加</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="DeleteS()">删除</a>
  <br>
  <br>
  <div>
   <table>
     <tr>
      <td>&nbsp;车票编号：<input type="text" id="ticketNumQuery" style="width:80px" value='' />
          &nbsp;起点站：<input type="text" id="start_stationQuery" style="width:80px" value=''/>
          &nbsp;终点站：<input type="text" id="end_stationQuery" style="width:80px" value=''/>&nbsp;</td>
      <td> <a href="#" iconCls="icon-search" class="easyui-linkbutton" onclick="doSearch()">查询</a></td>
     </tr>
   </table>
  </div>
  <br>
</div>
<div id="datagrid" ></div>
<!-- 添加 -->
<div id="ticketAdd" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="ticketAdd_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_ticketAdd" onkeydown="if(event.keyCode==13){ticketAdd();}">
	  <div style="height: 100%;width: 100%;margin:auto">
	   <table  border="0px" cellspacing="0" style="margin: auto" >
	     <tr><td style="width: 30%"><label>起点站：</label></td><td><input  type="text"  id="ticketAdd_start_station" maxlength="8" ></td><td></td></tr>
	     <tr><td style="width: 30%"><label>终点站：</label></td><td><input type="text"  id="ticketAdd_end_station" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>价格：</label></td><td><input type="text"  id="ticketAdd_price" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>出发时间：</label></td><td><input type="text"  id="ticketAdd_time" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>汽车编号：</label></td><td><input type="text"  id="ticketAdd_motorcoach_number" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>座位数量：</label></td><td><input type="text"  id="ticketAdd_seatNum" maxlength="11" ></td></tr>
	   </table>
	   <table style="margin: auto">
	   <tr><td style="width: 50%"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="ticketAdd();">提交</a></td><td><a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="ticketAddReset()">重置</a></td></tr>
	   </table>
	   </div>
<!--       
	</div>
</div>

<!-- 编辑 -->
<div id="ticketEdit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="ticketEdit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_ticketEdit" onkeydown="if(event.keyCode==13){ticketEdit();}">
	<input type="hidden" id="ticketEdit_ticketNum"></input>
       <div style="height: 100%;width: 100%;margin:auto">
	   <table  border="0px" cellspacing="0" style="margin: auto" >
	     <tr><td style="width: 30%"><label>起点站：</label></td><td><input  type="text"  id="ticketEdit_start_station" maxlength="8" ></td><td></td></tr>
	     <tr><td style="width: 30%"><label>终点站：</label></td><td><input type="text"  id="ticketEdit_end_station" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>价格：</label></td><td><input type="text"  id="ticketEdit_price" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>出发时间：</label></td><td><input type="text"  id="ticketEdit_time" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>汽车编号：</label></td><td><input type="text"  id="ticketEdit_motorcoach_number" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>座位数量：</label></td><td><input type="text"  id="ticketEdit_seatNum"  maxlength="2" ></td></tr>
	   </table>
	   <table style="margin: auto">
	   <tr><td style="width: 50%"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="ticketEdit();">提交</a></td><td><a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="closeDialog_edit()">取消</a></td></tr>
	   </table>
	   </div>
	</div>
</div>


</body>
</html>