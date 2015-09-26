<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="js/jquery.PrintArea.js"></script>
<link rel="stylesheet" href="css/print.css" type="text/css" media="print"/>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="js/ticketSell.js?time="></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>售票界面</title>
<%
    if(request.getSession().getAttribute("worker") == null) {
		response.sendRedirect("clearSession.jsp");
    }
%>
</head>
<body>
 <input id="workerName" type="hidden" value='${sessionScope.worker.workerName}'/>
 <input id="name" type="hidden" value='${sessionScope.worker.name}'/>
  <div id="ticketSellDg"></div>
  <div id="ticketSellTb">
   <br>
    <table>
      <tr>
        <td>&nbsp;起点站：<input type="text" id="start_stationQuery" style="width:80px" value=''/></td>
        <td>&nbsp;终点站：<input type="text" id="end_stationQuery" style="width:80px" value=''/>&nbsp;</td>
        <td>日期：<input id="dateQuery" class="easyui-datebox"  style="width:100px"></input>&nbsp;</td>
        <td><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a></td>
      <tr>
    </table>
  </div> 
  <div id="orderInfo" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<div  class="printDiv" style="width:450px ;height:210px;margin: auto" id="orderInfo_table" >	
	<table width="100%" height="100%" border="1px" style="margin: auto" cellpadding="0" cellspacing="0" >
	  <tr><td width="20%"><label>订单编号：</label></td><td width="30%"><label id="orderInfo_orderNum"></label></td><td width="20%"><label>价格：</label></td><td width="30%"><label id="orderInfo_price"></label></td></tr>
	  <tr><td ><label>起点站：</label></td><td><label id="orderInfo_start_station"></label></td><td ><label>终点站：</label></td><td ><label id="orderInfo_end_station"></label></td></tr>
	  <tr><td ><label>乘车日期：</label></td><td ><label id="orderInfo_date"></label></td><td ><label>出发时间：</label></td><td ><label id="orderInfo_time"></label></td></tr>
	  <tr><td ><label>汽车编号：</label></td><td ><label id="orderInfo_moNum"></label></td><td ><label>座位号：</label></td><td ><label id="orderInfo_seatNo"></label></td></tr>
	  <tr><td ><label>乘客：</label></td><td ><label id="orderInfo_passengerName"></label></td><td ><label>操作员：</label></td><td ><label id="orderInfo_worker_name"></label></td></tr>
	</table>
	</div>
	<br>
	<table style="margin: auto">
	   <tr><td><a href="#" class="easyui-linkbutton" icon="icon-print" onclick="printOrder()">打印</a></td><td><a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="closeDialog_orderInfo()">取消</a></td></tr>
	</table>
  </div>	
</body>
</html>