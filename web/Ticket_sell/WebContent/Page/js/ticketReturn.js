$(document).ready(function(){
	$('#ticketReturnDg').datagrid({
		border:true,
		fitColumns:true,
		fit:true,
		singleSelect:true,
		pagination: true,
	    rownumbers:true,//行号  
		url:'ticketReturn?function=query',
		toolbar:'#ticketReturnTb',
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
		          {field:'name',title:'操作员',width:90,
		        	  formatter:function(value,row,index){
		        	  if(row.worker){
		        		  return row.worker.name;
		        	  }
		          }},
		          {field:'opt',title:'退票',width:60,align:'center',
		        	  formatter:function(value,rec){
		        		  var orderNum=rec.orderNum;
		        		  var seatNo=rec.seatNo;
		        		  var date=rec.date;
		        		  var ticketNum=rec.ticket.ticketNum;
		        		  var btn = "<a class='editcls' onclick=\"returnTicket('"+orderNum+"','"+date+"','"+seatNo+"','"+ticketNum+"')\" href='javascript:void(0)'>退票</a>";  
		 	              return btn;  
		        	  }
		          }, 
		       
		          ]],
		          onLoadSuccess:function(data){  
		     	        $('.editcls').linkbutton({text:'退票',plain:true,iconCls:'icon-edit'});  
		     	     
		     	    },
		     	
	});
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
			$('#ticketReturnDg').datagrid('loadData',data);
		}
	})
}

function returnTicket(orderNum,date,seatNo,ticketNum){
	$.messager.confirm("提示", "确定退票吗？",function(r){
		if(r){
			$.ajax({
				async:false,
				cache:false,
				type:'POST',
				dataType:'json',
				url:'ticketReturn?function=ticketReturn',
				data:{
					'orderNum':orderNum,
					'date':date,
					'seatNo':seatNo,
					'ticketNum':ticketNum
				},error:function(){
				    messageShow("退票失败,请重试！");
				},success:function(data){
					messageShow("退票成功！");
					$('#ticketReturnDg').datagrid('reload');
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