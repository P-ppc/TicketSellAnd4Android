
$(document).ready(function(){
	$("#jdDg").datagrid({
		border:true,
	    fit:true,
	    fitColumns:false,  
	    idField:'jurisdictionNum',
	    singleSelect:false,
	    pagination: true,
	    rownumbers:true,//行号  
	    toolbar:'#jdTb',
	    url:'jdManage?function=query',
	    columns:[[
	             {field:'ck',checkbox:true},
	             {field:'jurisdictionNum',title:'权限编号',width:80},
	             {field:'jurisdictionName',title:'权限名称',width:80},
	             {field:'opt',title:'操作',width:70,align:'center',  
	 	            formatter:function(value,rec){  
	 	            	//添加编辑按钮
	 	            	var v1=rec.jurisdictionNum;
	 	            	var v2=rec.jurisdictionName;
	 	            	
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
	$('#jdAdd').dialog({
		title : '权限添加',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			jdAddReset();
		}
	});
}
function openDialog_add(){
	$('#jdAdd').dialog('open');
}
//关闭对话框
function closeDialog_add(){
	$('#jdAdd').dialog('close');
}

function setDialog_edit(){
	$('#jdEdit').dialog({
		title:'权限编辑',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			jdEditReset();
		}
	})
}
function openDialog_edit(jobNum,jobName){
	$("#jdEdit_jdNum").val(jobNum),
	$("#jdEdit_jdName").val(jobName),
	$('#jdEdit').dialog('open');
	
}
function closeDialog_edit(){
	$('#jdEdit').dialog('close');
}
function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		"jdNum":$("#jdNumQuery").val(),
		"jdName":$("#jdNameQuery").val()
			},
		url:'jdManage?function=query',//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},success:function(data){
			$('#jdDg').datagrid('loadData',data);
		}
	});
}

function jdAdd(){
	$.ajax({
		async:false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		"jdNum":$("#jdAdd_jdNum").val(),
		"jdName":$("#jdAdd_jdName").val()
			},
		url:'jdManage?function=addOrUpdate',//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},success:function(data){
			reloadTable();
			closeDialog_add();
		}
	});
}

function jdEdit(){
		$.ajax({
			async:false,
			cache:false,
			type: 'POST',
			dataType : "json",
			data:{
			"jdNum":$("#jdEdit_jdNum").val(),
			"jdName":$("#jdEdit_jdName").val()
				},
			url:'jdManage?function=addOrUpdate',//请求的action路径
			error: function () {//请求失败处理函数
				alert('请求失败');
			},success:function(data){
				reloadTable();
				closeDialog_edit();
			}
		});
}

function jdAddReset(){
	$("#jdAdd_jdNum").val(''),
	$("#jdAdd_jdName").val('')
}

function jdEditReset(){
	$("#jdEdit_jdNum").val(''),
	$("#jdEdit_jdName").val('')
}

function reloadTable(){
	$('#jdDg').datagrid('reload');
}

function Delete(rowId){
	 $.ajax({
			async : false,
			cache:false,
			type: 'POST',
			dataType : "json",
			url:"jdManage?function=delete&jdNum="+rowId,//请求的action路径
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
	var rows = $('#jdDg').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
	     Delete(rows[i].jurisdictionNum);
    }
	$('#jdDg').datagrid('uncheckAll');
}