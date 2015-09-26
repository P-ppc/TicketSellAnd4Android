<%@page import="com.ppc.Ticket_sell.bean.WorkerBean"%>
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
<script type="text/javascript" src="js/main.js?time="></script>
</head>
<%   
     Integer jd=0;
     if(request.getSession().getAttribute("worker") == null) { 
    			response.sendRedirect("clearSession.jsp");
     }else{
       WorkerBean worker=(WorkerBean)request.getSession().getAttribute("worker");
       if(worker.getJurisdiction()!=null &&  worker.getJurisdiction().getJurisdictionNum()!=null){
    	 jd=worker.getJurisdiction().getJurisdictionNum();
        }
     }
%> 

<body class="easyui-layout" style="width:100%;margin: auto" > 
<script type="text/javascript">
window.history.go(1);
</script>   
    <div region="north" href="top.jsp" style="height:100px;overflow-y: hidden" ></div>    
    <div data-options="region:'west',title:'导航',split:false" style="width:200px;">
      <div class="easyui-accordion" fit="true" border="false">
    		
			<div title="售票管理" selected="false">
			<ul >
				<li style="list-style-type:none;"><a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="navigation('售票管理', 'ticketSell.jsp','<%=jd%>')">售票管理</a></li>
				<li style="list-style-type:none;"><a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="navigation('取票管理', 'ticketGet.jsp','<%=jd%>')">取票管理</a></li>
				<li style="list-style-type:none;"><a href="#" class="easyui-linkbutton" plain="true"  iconCls="icon-redo" onclick="navigation('退票管理', 'ticketReturn.jsp','<%=jd%>')">退票管理</a></li>
			</ul>
			</div>
			<div title="车票管理" >
			<ul>
				<li style="list-style-type:none;"><a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="navigation('车票管理', 'ticketManage.jsp','<%=jd%>')">车票管理</a></li>
			</ul>
			</div>
			<div title="职工管理" selected="false">
			<ul>
				<li style="list-style-type:none;"><a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo"onclick="navigation('职工管理', 'workerManage.jsp','<%=jd%>')">职工管理</a></li>
<!-- 				<li style="list-style-type:none;"><a href="#"  class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="navigation('权限管理', 'jdManage.jsp')">权限管理</a></li> -->
<!-- 				<li style="list-style-type:none;"><a href="#"  class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="navigation('职位管理', 'jobManage.jsp')">职位管理</a></li> -->
			</ul>
			</div>
		</div>
     
    </div>    
    <div  id="center_region" data-options="region:'center'" title="欢迎" style="padding:0px;overflow: hidden;">
      <iframe id="center_iframe" src="welcome.jsp" style="height: 100%; width: 100%; " scrolling="no" frameborder="0"></iframe>
		
    </div> 

</body>    

</html>