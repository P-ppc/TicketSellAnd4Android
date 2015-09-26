<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职工管理</title>
<script src="js/jquery-1.8.0.js"></script>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="js/wokerManage.js?time="></script>
</head>

<%
    if(request.getSession().getAttribute("worker") == null) {
		response.sendRedirect("clearSession.jsp");
    }
%>

<body>

<!-- toolbar -->
 <div id="workerTb">
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog_add()">添加</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="DeleteS()">删除</a>
  <br>
  <br>
  <div>
   <table>
     <tr>
      <td>&nbsp;登录账号：<input type="text" id="workerNameQuery" style="width:80px" value='' />
          &nbsp;职工姓名：<input type="text" id="nameQuery" style="width:80px" value=''/>&nbsp;</td>
      <td><a href="#" iconCls="icon-search" class="easyui-linkbutton" onclick="doSearch()">查询</a></td>
     </tr>
   </table>
  </div>
  <br>
</div>

  <!-- datagrid -->
  <div id="workerDg"></div>
  
    <!--编辑-->
  <div id="workerEdit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="workerEdit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_workerEdit"  onkeydown="if(event.keyCode==13){workerEdit();}">
	  <div style="height: 100%;width: 100%;margin:auto">
	   <table  border="0px" cellspacing="0" style="margin: auto" >
	     <tr><td style="width: 30%"><label>登录账号：</label></td><td><input  type="text"  id="workerEdit_workerName" maxlength="8" readonly="readonly"></td><td></td></tr>
	     <tr><td style="width: 30%"><label>密码：</label></td><td><input type="text"  id="workerEdit_password" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>职工姓名：</label></td><td><input type="text"  id="workerEdit_name" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>职工电话：</label></td><td><input type="text"  id="workerEdit_tel" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>职位：</label></td><td><select id="jobCcEdit" style="width:100px"></select></td></tr>
	     <tr><td style="width: 30%"><label>权限：</label></td><td><select id="jdCcEdit" style="width:100px"></select></td></tr>
	   </table>
	   <table style="margin: auto">
	   <tr><td style="width: 50%"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="workerEdit();">提交</a></td><td><a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="closeDialog_edit()">取消</a></td></tr>
	   </table>
	  </div>
<!--         <ul> -->
<!-- 			<li> -->
<!-- 				<label>登录账号：</label> -->
<!-- 				<input type="text"  id="workerEdit_workerName" maxlength="8" readonly="readonly"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>密码：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerEdit_password" maxlength="10" required="true"></input> -->
<!-- 			</li> -->
<!-- 				<li> -->
<!-- 				<label>职工姓名：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerEdit_name" maxlength="20" required="true"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>职工电话：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerEdit_tel" maxlength="20" required="true"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>职位：</label> -->
<!-- 				<select id="jobCcEdit" style="width:100px"></select> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>权限：</label> -->
<!-- 				<select id="jdCcEdit" style="width:100px"></select> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="workerEdit();">提交</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="workerEditReset();">重置</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
	</div>
</div>
  <!-- 添加 -->
  <div id="workerAdd" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="workerAdd_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_workerAdd" onkeydown="if(event.keyCode==13){workerAdd();}">
	   <div style="height: 100%;width: 100%;margin:auto">
	   <table  border="0px" cellspacing="0" style="margin: auto" >
	     <tr><td style="width: 30%"><label>登录账号：</label></td><td><input  type="text"  id="workerAdd_workerName" maxlength="8" ></td><td></td></tr>
	     <tr><td style="width: 30%"><label>密码：</label></td><td><input type="text"  id="workerAdd_password" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>职工姓名：</label></td><td><input type="text"  id="workerAdd_name" maxlength="8" ></td></tr>
	     <tr><td style="width: 30%"><label>职工电话：</label></td><td><input type="text"  id="workerAdd_tel" maxlength="11" ></td></tr>
	     <tr><td style="width: 30%"><label>职位：</label></td><td><select id="jobCcAdd" style="width:100px"></select></td></tr>
	     <tr><td style="width: 30%"><label>权限：</label></td><td><select id="jdCcAdd" style="width:100px"></select></td></tr>
	   </table>
	   <table style="margin: auto">
	   <tr><td style="width: 50%"><a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="workerAdd();">提交</a></td><td><a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="workerAddReset();">重置</a></td></tr>
	   </table>
	  </div>
<!--         <ul> -->
<!-- 			<li> -->
<!-- 				<label>登录账号：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerAdd_workerName" maxlength="8" required="true"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>密码：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerAdd_password" maxlength="10" required="true"></input> -->
<!-- 			</li> -->
<!-- 				<li> -->
<!-- 				<label>职工姓名：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerAdd_name" maxlength="20" required="true"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>职工电话：</label> -->
<!-- 				<input type="text" class="easyui-validatebox" id="workerAdd_tel" maxlength="20" required="true"></input> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>职位：</label> -->
<!-- 				<select id="jobCcAdd" style="width:100px"></select> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<label>权限：</label> -->
<!-- 				<select id="jdCcAdd" style="width:100px"></select> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="workerAdd();">提交</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="workerAddReset();">重置</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
	</div>
</div>
</body>
</html>