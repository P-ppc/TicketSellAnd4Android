
//加载完成后函数
$(document).ready(function(){
$('#datagrid').datagrid({  
	    border:true,
	    fit:true,
	    fitColumns:true,  
	    idField:'ticketNum',
	    singleSelect:false,
	    pagination: true,
	    rownumbers:true,//行号  
	    toolbar:'#tb',
	    url:'ticketInfo?function=query', 
	    columns:[[  
	        {field:'ck',checkbox:true},
	        {field:'ticketNum',title:'编号',width:60},  
	        {field:'start_station',title:'起点站',width:100},
	        {field:'end_station',title:'终点站',width:100},
	        {field:'price',title:'价格',width:90},
	        {field:'time',title:'出发时间',width:100},
	        {field:'motorcoach_number',title:'汽车编号',width:110},
	        {field:'seatNum',title:'座位数量',width:118},
	        {field:'opt',title:'操作',width:70,align:'center',  
	            formatter:function(value,rec){  
	            	//添加编辑按钮
	            	var v1=rec.ticketNum;
	            	var v2=rec.start_station;
	            	var v3= rec.end_station;
	            	var v4=rec.price;
	            	var v5=rec.time;
	            	var v6=rec.motorcoach_number;
	            	var v7=rec.seatNum;
	                var btn = "<a class='editcls' onclick=\"openDialog_edit('"+$.trim(v1)+"','"+$.trim(v2)+"','"+$.trim(v3)+"','"+$.trim(v4)+"','"+$.trim(v5)+"','"+$.trim(v6)+"','"+$.trim(v7)+"')\" href='javascript:void(0)'>编辑</a>";  
	                return btn;  
	            }  
	        },
	    ]], 
	    //内容里加编辑图标
	    onLoadSuccess:function(data){  
	        $('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
	    }
       
   
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


//其他函数

function openDialog_add(){
	$('#ticketAdd').dialog('open');
}
//关闭对话框
function closeDialog_add(){
	$('#ticketAdd').dialog('close');
}
//添加
function setDialog_add(){
	$('#ticketAdd').dialog({
		title : '车票添加',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			ticketAddReset();
		}
	});
}
//编辑
function setDialog_edit(){
	$('#ticketEdit').dialog({
		title:'车票编辑',
		
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
			ticketEditReset();
		}
	})
}

function closeDialog_edit(){
	$('#ticketEdit').dialog('close');
}
function openDialog_edit(ticketNum,start_station,end_station,price,time,motorcoach_number,seatNum){
	
	
	$("#ticketEdit_ticketNum").val(ticketNum);
	$("#ticketEdit_start_station").val(start_station);
	$("#ticketEdit_end_station").val(end_station);
	$("#ticketEdit_price").val(price);
	$("#ticketEdit_time").val(time);
	$("#ticketEdit_motorcoach_number").val(motorcoach_number);
	$("#ticketEdit_seatNum").val(seatNum);
	$('#ticketEdit').dialog('open');
	
}

function DeleteS()
{
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows.length>0){
		 $.messager.confirm("提示框", "确定要删除吗？", function (r) {  
			 if(r){
				 for(var i=0; i<rows.length; i++){
				     Delete(rows[i].ticketNum);
			       }
				 $('#datagrid').datagrid('uncheckAll'); 
				 messageShow('删除成功!');
			 }
			
		    });
	}
}

 //根据ID删除信息
function Delete(rowId)
{
             //alert("delete");
             var url="ticketInfo?function=delete&ticketNum="+rowId;
             
             changeStatus(url);
		

}

function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
			"ticketNum":$("#ticketNumQuery").val(),
		   "start_station":$("#start_stationQuery").val(),
		   "end_station":$("#end_stationQuery").val()
			},
		url:'ticketInfo?function=query',//请求的action路径
		error: function () {//请求失败处理函数
			alert('请求失败');
		},success:function(data){
			$('#datagrid').datagrid('loadData',data);
		}
	});
}
	
/**
 * 修改状态的Ajax
 * @param url
 * @return
 */
function changeStatus(url){
	$.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		url:url,//请求的action路径
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
//重载DATAGRID
function reloadTable(){
	$('#datagrid').datagrid('reload');
}
//增加车票
function ticketAdd(){
	var flag=ticketAddVal();
	if(flag==false){
		messageShow('请输入完整信息！')
	}else{
	$.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		   "start_station":$("#ticketAdd_start_station").val(),
		   "end_station":$("#ticketAdd_end_station").val(),
		   "price":$("#ticketAdd_price").val(),
		   "time":$("#ticketAdd_time").val(),
		   "motorcoach_number":$("#ticketAdd_motorcoach_number").val(),
		   "seatNum":$("#ticketAdd_seatNum").val()
			},
		
		url:'ticketInfo?function=addOrUpdate',//请求的action路径
		error: function () {//请求失败处理函数
			messageShow("输入格式错误！");
		},success:function(data){
			if(data!=null){//返回异常信息
				$.messager.alert('错误提示',data.errorMsg,'error');
			}
			messageShow("添加成功!");
			reloadTable();
			$("#ticketAdd").dialog('close');
			
		}
	 });
	}
}
function ticketEdit(){
	var flag=ticketEditVal();
	if(flag==false){
		messageShow('请输入完整信息！');
	} else{
	$.ajax({
		async : false,
		cache:false,
		type: 'POST',
		dataType : "json",
		data:{
		   "ticketNum":$("#ticketEdit_ticketNum").val(),
		   "start_station":$("#ticketEdit_start_station").val(),
		   "end_station":$("#ticketEdit_end_station").val(),
		   "price":$("#ticketEdit_price").val(),
		   "time":$("#ticketEdit_time").val(),
		   "motorcoach_number":$("#ticketEdit_motorcoach_number").val(),
		   "seatNum":$("#ticketEdit_seatNum").val()
			},
		
		url:'ticketInfo?function=addOrUpdate',//请求的action路径
		error: function () {//请求失败处理函数
			messageShow("输入格式错误！");
		},success:function(data){
			if(data!=null){//返回异常信息
				$.messager.alert('错误提示',data.errorMsg,'error');
			}
			$('#datagrid').datagrid('clearSelections');
			messageShow("修改成功！");
			reloadTable();
			$("#ticketEdit").dialog('close');
			
		}
	});
	}
}

function ticketAddReset(){
	$("#ticketAdd_message").html("");
	$("#ticketAdd_start_station").val("");
	$("#ticketAdd_end_station").val("");
	$("#ticketAdd_price").val("");
	$("#ticketAdd_time").val("");
	$("#ticketAdd_motorcoach_number").val("");
	$("#ticketAdd_seatNum").val("");
}

function ticketEditReset(){
	$("#ticketEdit_message").html("");
	$("#ticketEdit_ticketNum").val("");
	$("#ticketEdit_start_station").val("");
	$("#ticketEdit_end_station").val("");
	$("#ticketEdit_price").val("");
	$("#ticketEdit_time").val("");
	$("#ticketEdit_motorcoach_number").val("");
	$("#ticketEdit_seatNum").val("");
}
 
function ticketAddVal(){
	if($("#ticketAdd_start_station").val()==null || $("#ticketAdd_start_station").val()==''){
		return false;
	}
	if($("#ticketAdd_end_station").val()==null || $("#ticketAdd_end_station").val()==''){
		return false;
	}
	if($("#ticketAdd_price").val()==null || $("#ticketAdd_price").val()==''){
		return false;
	}
	if($("#ticketAdd_time").val()==null || $("#ticketAdd_time").val()==''){
		return false;
	}
	if($("#ticketAdd_motorcoach_number").val()==null || $("#ticketAdd_motorcoach_number").val()==''){
		return false;
	}
	if($("#ticketAdd_seatNum").val()==null || $("#ticketAdd_seatNum").val()==''){
		return false;
	}
	return true;
}
function ticketEditVal(){
	
	if($("#ticketEdit_start_station").val()==null || $("#ticketEdit_start_station").val()==''){
		return false;
	}
	if($("#ticketEdit_end_station").val()==null || $("#ticketEdit_end_station").val()==''){
		return false;
	}
	if($("#ticketEdit_price").val()==null || $("#ticketEdit_price").val()==''){
		return false;
	}
	if($("#ticketEdit_time").val()==null || $("#ticketEdit_time").val()==''){
		return false;
	}
	if($("#ticketEdit_motorcoach_number").val()==null || $("#ticketEdit_motorcoach_number").val()==''){
		return false;
	}
	if($("#ticketEdit_seatNum").val()==null || $("#ticketEdit_seatNum").val()==''){
		return false;
	}
	return true;
}

function messageShow(msg){
	$.messager.show({  
        title: '提示',  
        msg: msg,  
        showType: 'slide'  
    }); 
}
