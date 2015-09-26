$(document).ready(function(){
	$('#ticketGetDg').datagrid({
		border:true,
		fitColumns:true,
		fit:true,
		singleSelect:false,
		pagination: true,
	    rownumbers:true,//行号  
		url:'ticketGet?function=query',
		toolbar:'#ticketGetTb',
		columns:[[
		          {field:'orderNum',title:'售票单号',width:50},
		          {field:'start_station',title:'起点站',width:60,
		        	  formatter:function(value,row,index){
		        		  if(row.ticket){
		        			  return row.ticket.start_station;
		        		  }
		        	  }},  
		          {field:'end_station',title:'终点',width:60,
		        	  formatter:function(value,row,index){
		        		  if(row.ticket){
		        			  return row.ticket.end_station;
		        		  }
		        	  }},
		          {field:'price',title:'价格',width:50,
		        		  formatter:function(value,row,index){
		        			  if(row.ticket){
		        				  return row.ticket.price;
		        			  }
		        		  }},
		          {field:'passengerName',title:'乘客姓名',width:70},
		          {field:'passengerId',title:'乘客证件号',width:105},
		          {field:'date',title:'乘车日期',width:70},
		          {field:'time',title:'发车时间',width:65,
		        	  formatter:function(value,row,index){
		        		  if(row.ticket){
		        			  return row.ticket.time;
		        		  }
		        	  }},
		          {field:'orderTime',title:'订单生成时间',width:110},
		          {field:'state',title:'状态',width:60,
		        	  formatter:function(value,row,index){
		        		  if(value.state){
		        			  return value.state;
		        		  }
		        	  }},
		          {field:'seatNo',title:'座位号',width:50},
		          {field:'workerName',title:'操作员',width:90},
		          {field:'opt',title:'取票',width:60,align:'center',
		        	  formatter:function(value,rec){
		        		  var orderNum=rec.orderNum;
		        		  var seatNo=rec.seatNo;
		        		  var date=rec.date;
		        		  var ticketNum=rec.ticket.ticketNum;
//		        		  var passengerName=rec.passengerName;
//		        		  var passengerId=rec.passengerId;
//		        		 
		        		  var btn = "<a class='editcls' onclick=\"getTicket('"+orderNum+"','"+date+"','"+seatNo+"','"+ticketNum+"')\" href='javascript:void(0)'>取票</a>";  
		 	              return btn;  
		        	  }
		          }, 
		       
		          ]],
		          onLoadSuccess:function(data){  
		     	        $('.editcls').linkbutton({text:'取票',plain:true,iconCls:'icon-edit'});  
		     	     
		     	    },
		     	
	});
	setDialog_orderInfo();
	closeDialog_orderInfo();
});


function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type:'POST',
		dataType:'json',
		data:{
			'orderNum':$('#orderNumQuery').val(),
			'passengerName':$('#passengerNameQuery').val(),
			'passengerId':$('#passengerIdQuery').val()
		},
		url:'ticketGet?function=query',
		error:function(){
			alert('error');
		},success:function(data){
			$('#ticketGetDg').datagrid('loadData',data);
		}
	})
}

function getTicket(orderNum,date,seatNo,ticketNum,passengerName,passengetId,orderTime){
	$.messager.confirm("提示", "确定取票吗？",function(r){
		if(r){
			$.ajax({
				async:false,
				cache:false,
				type:'POST',
				dataType:'json',
				url:'ticketGet?function=getTicket',
				data:{
					'orderNum':orderNum,
					'workerName':$('#workerName').val()
				},error:function(){
					messageShow("取票出错！请重试");
					$('#ticketGetDg').datagrid('reload');
				},success:function(data){
					if(data.data=="fail"){
						messageShow("取票出错！");
						$('#ticketGetDg').datagrid('reload');
					}else{
						messageShow("取票成功！");
						$('#ticketGetDg').datagrid('reload');
						if(data.rows.date!=null){
							document.getElementById('orderInfo_date').innerText=data.rows.date;
						}
						if(data.rows.orderNum!=null){
							document.getElementById('orderInfo_orderNum').innerText=data.rows.orderNum;
						}
						if(data.rows.seatNo!=null){
							document.getElementById('orderInfo_seatNo').innerText=data.rows.seatNo;
						}
						if(data.rows.passengerName!=null){
							document.getElementById('orderInfo_passengerName').innerText=data.rows.passengerName;
						}
						if(data.rows.ticket.price!=null){
							document.getElementById('orderInfo_price').innerText=data.rows.ticket.price;
						}
						if(data.rows.ticket.time!=null){
							document.getElementById('orderInfo_time').innerText=data.rows.ticket.time;
						}
						if(data.rows.ticket.motorcoach_number!=null){
							document.getElementById('orderInfo_moNum').innerText=data.rows.ticket.motorcoach_number;
						}
						if(data.rows.ticket.start_station!=null){
							document.getElementById('orderInfo_start_station').innerText=data.rows.ticket.start_station;
						}
						if(data.rows.ticket.end_station!=null){
							document.getElementById('orderInfo_end_station').innerText=data.rows.ticket.end_station;
						}
						if($("#name").val()!=null){
							document.getElementById('orderInfo_worker_name').innerText=$("#name").val();
						}
						openDialog_orderInfo();
					}
				}
			})
			
		}
	});
	
}
function messageShow(msg){
	$.messager.show({
		title:'提示',
		msg:msg,
		showType: 'slide'  
	});
}
function setDialog_orderInfo(){
	$("#orderInfo").dialog({
		title:'车票信息',
		modal: true,         	//模式窗口：窗口背景不可操作
		collapsible : true,  	//可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : false,    	//可拖动边框大小
		onClose : function(){   //继承自panel的关闭事件
		}
	})
}
function openDialog_orderInfo(){
	
	$("#orderInfo").dialog('open');
}
function closeDialog_orderInfo(){
	$("#orderInfo").dialog('close');
}
function printOrder(){
	$("div#orderInfo_table").printArea();
}