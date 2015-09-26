<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>汽车售票系统</title>
	<script src="js/jquery-1.8.0.js"></script>
<script src="js/jquery-easyui-1.3.5/jquery.easyui.min.js" type="text/javascript"></script>
<link href="js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-easyui-1.3.5/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/main.css" type="text/css"/>
<script type="text/javascript" src="js/login.js?time="></script>
<link rel="stylesheet" href="css/login.css" type="text/css" >

</head>
<body style="visibility:visible" >
   
	<div class="easyui-dialog" style="width:500px;height:300px;background:#fafafa;overflow:hidden"
			title="登录系统" closable="false" border="false">
			<div id="win"></div> 
		<div class="header" style="height:60px;">
			<div class="toptitle">汽车售票系统</div>
		</div>
		<div style="padding:60px 0;">
		
				<div style="padding-left:150px">
					<table cellpadding="0" cellspacing="3">
						<tr>
							<td>登录帐号:</td>
							<td><input id="workerName" onblur="workerNameVali()"></input></td>
							<td><label id="workerNameValidate" style="color: red"></label></td>
						</tr>
						<tr>
							<td>登录密码:</td>
							<td><input type="password" id="password" onblur="passwordVali()"></input></td>
							<td><label id="passwordValidate" style="color: red"></label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><label id="message" style="color: red"></label></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input  class="login" type="button" onclick="Login()"  style="width:74px;height:21px;border:0" ></input>
							</td>
							<td></td>
						</tr>
					</table>
				</div>
		</div>
	</div>
</body>
</html>