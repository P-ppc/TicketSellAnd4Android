<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.8.0.js"></script>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jobManage.js"></script>
<title>职位管理</title>
</head>
<body>
  <!-- toolbar -->
  <div id="jobTb">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog_add()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="Deletes()">删除</a>
    <br>
    <table>
    <tr>
    <td>&nbsp;职位编号：<input type="text" style="width:80px" id="jobNumQuery" />
    &nbsp;职位名称:<input type="text" id="jobNameQuery" style="width:80px" />&nbsp;</td>
    <td><a href="#" iconCls="icon-search" class="easyui-linkbutton" onclick="doSearch()">查询</a></td>
    </tr>
    </table>
    </div>
  </div>
  <!-- 表格 -->
  <div id="jobDg"></div>
  
  <!-- 添加 -->
  <div id="jobAdd" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="jobAdd_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_jobAdd" onkeydown="if(event.keyCode==13){jobAdd();}">
        <ul>
			<li>
				<label>职位编号：</label>
				<input type="text" class="easyui-validatebox" id="jobAdd_jobNum" maxlength="8" required="true"></input>
			</li>
			<li>
				<label>职位名称：</label>
				<input type="text" class="easyui-validatebox" id="jobAdd_jobName" maxlength="8" required="true"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="jobAdd();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="jobAddReset();">重置</a>
			</li>
		</ul>
	</div>
</div>
<!-- 编辑 -->
    <div id="jobEdit" icon="icon-save"
	style="padding: 5px; width: 500px; height: 300px;">
	<h5 id="jobEdit_message" style="color: red;"></h5>
	<div class="ToolTip_Form" id="table_jobEdit" onkeydown="if(event.keyCode==13){jobEdit();}">
        <ul>
			<li>
				<label>职位编号：</label>
				<input type="text" class="easyui-validatebox" id="jobEdit_jobNum" maxlength="8" required="true"></input>
			</li>
			<li>
				<label>职位名称：</label>
				<input type="text" class="easyui-validatebox" id="jobEdit_jobName" maxlength="8" required="true"></input>
			</li>
			<li>
				<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="jobEdit();">提交</a>
				<a href="#" class="easyui-linkbutton" icon="icon-redo" onclick="jobEditReset();">重置</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>