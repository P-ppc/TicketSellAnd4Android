$(document).ready(function(){
	$("#workerDg").datagrid({
		border:true,
	    fit:true,
	    fitColumns:true,  
	    idField:'workerName',
	    singleSelect:false,
	    pagination: true,
	    rownumbers:true,//行号  
	    toolbar:'#workerTb',
	    url:'workerManage?function=query',
	    columns:[[
	             {field:'ck',checkbox:true},
	             {field:'workerName',title:'登陆账号',width:80},
	             {field:'password',title:'密码',width:80},
	             {field:'name',title:'职工姓名',width:80},
	             {field:'tel',title:'电话号码',width:80},
	             {field:'job',title:'职位',width:80,formatter:function(value,row,index){
	            	 if(row.job){
	            		 return row.job.jobName;
	            	 }
	             }},
	             {field:'jurisdiction',title:'权限',width:80,formatter:function(value,row,index){
	            	 if(value.jurisdictionName){
	            		 return value.jurisdictionName;
	            	 }
	             }},
	             {field:'opt',title:'操作',width:70,align:'center',  
	 	            formatter:function(value,rec){  
	 	            	//添加编辑按钮
	 	            	var v1=rec.workerName;
	 	            	var v2=rec.password;
	 	            	var v3=rec.name;
	 	            	var v4=rec.tel;
	 	            	
	 	            	var v5;
	 	            	if(rec.job!=null){
	 	            		v5=rec.job.jobNum;
	 	            	}
	 	            	var v6=rec.jurisdiction.jurisdictionNum;
	 	                var btn = "<a class='editcls' onclick=\"openDialog_edit('"+$.trim(v1)+"','"+$.trim(v2)+"','"+$.trim(v3)+"','"+$.trim(v4)+"','"+$.trim(v5)+"','"+$.trim(v6)+"')\" href='javascript:void(0)'>编辑</a>";  
	 	                return btn;  
	 	            }  
	             },
	             ]],
	             //内容里加编辑图标
	     	    onLoadSuccess:function(data){  
	     	        $('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
	     	    }
	});
	

	
	var jobUrl = "jobManage?function=query";
	$.getJSON(jobUrl, function(json) {
	  $('#jobCcQuery').combobox({
	    data : json.rows,
	    editable:false,
	    valueField:'jobNum',
	    textField:'jobName',
	    panelHeight:'auto'
	  });
	});
    
	var jdUrl="jdManage?function=query";
	$.getJSON(jdUrl,function(json){
		$('#jdCcQuery').combobox({
			    data : json.rows,
			    editable:false,
			    valueField:'jurisdictionNum',
			    textField:'jurisdictionName',
			    panelHeight:'auto'
		});
	});
	
	//网页加载完成时，加载ADD窗口
	setDialog_add(),
	//关闭ADD窗口
	closeDialog_add(),
	//加载编辑窗口
	setDialog_edit(),
	//关闭
	closeDialog_edit()	
});


function setDialog_add(){
	var jobUrl = "jobManage?function=query";
	$.getJSON(jobUrl, function(json) {
	  $('#jobCcAdd').combobox({
	    data : json.rows,
	    editable:false,
	    valueField:'jobNum',
	    textField:'jobName',
	    panelHeight:'auto'
	  });
	});
	var jdUrl="jdManage?function=query";
	$.getJSON(jdUrl,function(json){
		$('#jdCcAdd').combobox({
			    data : json.rows,
			    editable:false,
			    valueField:'jurisdictionNum',
			    textField:'jurisdictionName',
			    panelHeight:'auto'
		});
	});
	$('#workerAdd').dialog({
		title : '职工添加',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			workerAddReset();
		}
	});
}
function openDialog_add(){
	
	$('#workerAdd').dialog('open');
}
//关闭对话框
function closeDialog_add(){
	$('#workerAdd').dialog('close');
}

function setDialog_edit(){
	var jdUrl="jdManage?function=query";
	$.getJSON(jdUrl,function(json){
		$('#jdCcEdit').combobox({
			    data : json.rows,
			    editable:false,
			    valueField:'jurisdictionNum',
			    textField:'jurisdictionName',
			    panelHeight:'auto'
		});
	});
	var jobUrl="jobManage?function=query";
	$.getJSON(jobUrl,function(json){
		$('#jobCcEdit').combobox({
			    data : json.rows,
			    editable:false,
			    valueField:'jobNum',
			    textField:'jobName',
			    panelHeight:'auto'
		});
	});
	$('#workerEdit').dialog({
		title:'职工编辑',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			workerEditReset();
		}
	})
}
function openDialog_edit(workerName,password,name,tel,job,jd){
	$('#workerEdit_workerName').val(workerName);
	$('#workerEdit_password').val(password);
	$('#workerEdit_name').val(name);
	$('#workerEdit_tel').val(tel);
	$('#jobCcEdit').combobox('setValue',job); 
	$('#jdCcEdit').combobox('setValue',jd); 
	$('#workerEdit').dialog('open');
}
function closeDialog_edit(){
	$('#workerEdit').dialog('close');
}

function workerEditReset(){

}

function workerAddReset(){
	
	$('#workerAdd_workerName').val('');
	$('#workerAdd_password').val('');
	$('#workerAdd_name').val('');
	$('#workerAdd_tel').val('');
//	$('#jobCcAdd').combobox('setValue',null); 
//	$('#jdCcAdd').combobox('setValue',null); 
}
function reloadTable(){
	$('#workerDg').datagrid('reload');
}

function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type:"POST",
		dataType:'json',
		data:{
			'workerName':$('#workerNameQuery').val(),
			'name':$('#nameQuery').val(),
		},
	    url:'workerManage?function=query',
	    error:function(){
	    	messageShow("出错了！");
	    },success:function(data){
	    	$('#workerDg').datagrid('loadData',data);
	    }
	});
}

function workerAdd(){
	var flag=workerAddVal();
	if(flag==false){
		messageShow('请输入完整信息！')
	}else{
	 $.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		   "workerName":$("#workerAdd_workerName").val(),
		   "password":$("#workerAdd_password").val(),
		   "name":$("#workerAdd_name").val(),
		   "tel":$("#workerAdd_tel").val(),
		   "jobNum":$("#jobCcAdd").combobox('getValue'),
		   "jobName":$("#jobCcAdd").combobox('getText'),
		   "jdNum":$("#jdCcAdd").combobox('getValue'),
		   "jdName":$('#jdCcAdd').combobox('getText')
			},
		url:'workerManage?function=addOrUpdate',//请求的action路径
		error: function () {//请求失败处理函数
			messageShow("请输入正确信息！");
		},success:function(data){
			if(data!=null){//返回异常信息
				$.messager.alert('错误提示',data.errorMsg,'error');
			}
			messageShow("添加成功！");
			reloadTable();
			$("#workerAdd").dialog('close');
		}
	});
  }
}
function workerEdit(){
	var flag=workerEditVal();
	if(flag==false){
		messageShow('请输入完整信息！')
	}
    else{
    		$.ajax({
    			async : false,
    			cache:false,
    			type: 'POST',
    			dataType : "json",
    			data:{
    			   "workerName":$("#workerEdit_workerName").val(),
    			   "password":$("#workerEdit_password").val(),
    			   "name":$("#workerEdit_name").val(),
    			   "tel":$("#workerEdit_tel").val(),
    			   "jobNum":$("#jobCcEdit").combobox('getValue'),
    			   "jobName":$("#jobCcEdit").combobox('getText'),
    			   "jdNum":$("#jdCcEdit").combobox('getValue'),
    			   "jdName":$('#jdCcEdit').combobox('getText')
    				},
    			url:'workerManage?function=addOrUpdate',//请求的action路径
    			error: function () {//请求失败处理函数
    				messageShow("请输入正确信息！");
    			},success:function(data){
    				if(data!=null){//返回异常信息
    					$.messager.alert('错误提示',data.errorMsg,'error');
    				}
    				$('#workerDg').datagrid('clearSelections');
    				messageShow("修改成功！");
    				reloadTable();
    				$("#workerEdit").dialog('close');
    			}
    		});
  }
}

function Delete(rowId){
	$.ajax({
		async:false,
		cache:false,
		type:'POST',
		dataType:'json',
		url:'workerManage?function=delete&workerName='+rowId,
		error:function(){
			messageShow("删除出错！");
		},success:function(){
			reloadTable();
		}
	})
}

function DeleteS(){
	var rows = $('#workerDg').datagrid('getSelections');
	
	if(rows.length>0){
		 $.messager.confirm("提示框", "确定要删除吗？", function (r) {  
		        if (r) {  
		        	for(var i=0; i<rows.length; i++){
		       	     Delete(rows[i].workerName);
		           }
		       	 $('#workerDg').datagrid('uncheckAll');
		       	 messageShow('删除成功!');
		        }  
		    });
	}
	
}

function workerEditVal(){
	var flag=true;
	if($("#workerEdit_workerName").val()==null || $("#workerEdit_workerName").val()=="" ){
		flag=false;
	}
	if($("#workerEdit_password").val()==null ||$("#workerEdit_password").val()==""){
		flag=false;
	}
	if($("#workerEdit_name").val()==null || $("#workerEdit_name").val()==""){
		flag=false;
	}
	if($("#workerEdit_tel").val()==null || $("#workerEdit_tel").val()==""){
		flag=false;
	}
	if($("#jobCcEdit").combobox('getValue')==null || $("#jobCcEdit").combobox('getValue')==""){
		flag=false;
	}
	if($("#jobCcEdit").combobox('getText')==null || $("#jobCcEdit").combobox('getText')=="" ){
		flag=false;
	}
	if($("#jdCcEdit").combobox('getValue')==null || $("#jdCcEdit").combobox('getValue')=="" ){
		flag=false;
	}
	if($("#jdCcEdit").combobox('getText')==null ||$("#jdCcEdit").combobox('getText')=="" ){
		flag=false;
	}
	return flag;
		
}

function workerAddVal(){
	var flag=true;
	if($("#workerAdd_workerName").val()==null || $("#workerAdd_workerName").val()=="" ){
		flag=false;
	}
	if($("#workerAdd_password").val()==null ||$("#workerAdd_password").val()==""){
		flag=false;
	}
	if($("#workerAdd_name").val()==null || $("#workerAdd_name").val()==""){
		flag=false;
	}
	if($("#workerAdd_tel").val()==null || $("#workerAdd_tel").val()==""){
		flag=false;
	}
	if($("#jobCcAdd").combobox('getValue')==null || $("#jobCcAdd").combobox('getValue')==""){
		flag=false;
	}
	if($("#jobCcAdd").combobox('getText')==null || $("#jobCcAdd").combobox('getText')=="" ){
		flag=false;
	}
	if($("#jdCcAdd").combobox('getValue')==null || $("#jdCcAdd").combobox('getValue')=="" ){
		flag=false;
	}
	if($("#jdCcAdd").combobox('getText')==null ||$("#jdCcAdd").combobox('getText')=="" ){
		flag=false;
	}
	return flag;
	
}

function messageShow(msg){
	$.messager.show({  
        title: '提示',  
        msg: msg,  
        showType: 'slide'  
    });  
}

function checkTel(tel){
    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
    if(isMob.test(tel)|| isPhone.test(tel)){
        return true;
    }
    else{
        return false;
    }
 }