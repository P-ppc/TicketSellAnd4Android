
$(document).ready(function(){
	$("#jobDg").datagrid({
		border:true,
	    fit:true,
	    fitColumns:false,  
	    idField:'jobNum',
	    singleSelect:false,
	    pagination: true,
	    rownumbers:true,//行号  
	    toolbar:'#jobTb',
	    url:'jobManage?function=query',
	    columns:[[
	             {field:'ck',checkbox:true},
	             {field:'jobNum',title:'职位编号',width:80},
	             {field:'jobName',title:'职位名称',width:80},
	             {field:'opt',title:'操作',width:70,align:'center',  
	 	            formatter:function(value,rec){  
	 	            	//添加编辑按钮
	 	            	var v1=rec.jobNum;
	 	            	var v2=rec.jobName;
	 	            	
	 	                var btn = "<a class='editcls' onclick=\"openDialog_edit('"+$.trim(v1)+"','"+$.trim(v2)+"')\" href='javascript:void(0)'>编辑</a>";  
	 	                return btn;  
	 	            }  
	             },
	             ]],
	             //内容里加编辑图标
	     	    onLoadSuccess:function(data){  
	     	        $('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
	     	    }
	});
setDialog_add(),
closeDialog_add(),
setDialog_edit(),
closeDialog_edit()
});

//其他方法
function setDialog_add(){
	$('#jobAdd').dialog({
		title : '职位添加',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			jobAddReset();
		}
	});
}
function openDialog_add(){
	$('#jobAdd').dialog('open');
}
//关闭对话框
function closeDialog_add(){
	$('#jobAdd').dialog('close');
}

function setDialog_edit(){
	$('#jobEdit').dialog({
		title:'职工编辑',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			jobEditReset();
		}
	})
}
function openDialog_edit(jobNum,jobName){
	$("#jobEdit_jobNum").val(jobNum),
	$("#jobEdit_jobName").val(jobName),
	$('#jobEdit').dialog('open');
	
}
function closeDialog_edit(){
	$('#jobEdit').dialog('close');
}
function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		"jobNum":$("#jobNumQuery").val(),
		"jobName":$("#jobNameQuery").val()
			},
		url:'jobManage?function=query',//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},success:function(data){
			$('#jobDg').datagrid('loadData',data);
		}
	});
}

function jobAdd(){
	$.ajax({
		async:false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		"jobNum":$("#jobAdd_jobNum").val(),
		"jobName":$("#jobAdd_jobName").val()
			},
		url:'jobManage?function=addOrUpdate',//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},success:function(data){
			reloadTable();
			closeDialog_add();
		}
	});
}

function jobEdit(){
		$.ajax({
			async:false,
			cache:false,
			type: 'POST',
			dataType : "json",
			data:{
			"jobNum":$("#jobEdit_jobNum").val(),
			"jobName":$("#jobEdit_jobName").val()
				},
			url:'jobManage?function=addOrUpdate',//请求的action路径
			error: function () {//请求失败处理函数
				alert('请求失败');
			},success:function(data){
				reloadTable();
				closeDialog_edit();
			}
		});
}

function jobAddReset(){
	$("#jobAdd_jobNum").val(''),
	$("#jobAdd_jobName").val('')
}

function jobEditReset(){
	$("#jobEdit_jobNum").val(''),
	$("#jobEdit_jobName").val('')
}

function reloadTable(){
	$('#jobDg').datagrid('reload');
}

function Delete(rowId){
	 $.ajax({
			async : false,
			cache:false,
			type: 'POST',
			dataType : "json",
			url:"jobManage?function=delete&jobNum="+rowId,//请求的action路径
			error: function () {//请求失败处理函数
				alert('请求失败');
			},success:function(data){
				if(data!=null){//返回异常信息
					$.messager.alert('错误提示',data.errorMsg,'error');
				}
				reloadTable();
			}
		});
}

function Deletes(){
	var rows = $('#jobDg').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
	     Delete(rows[i].jobNum);
    }
	$('#jobDg').datagrid('uncheckAll');
}