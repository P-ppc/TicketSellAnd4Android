
$(document).ready(function(){
	var date= new Date();
	$('#dateQuery').datebox({
		editable:false,
		//在现在和3天后之间选择日期，否则会自动选择今天
		onSelect:function(date){
			var newDate=myformatter(date);
			var now=myformatter(new Date());
			var dd=GetDateStr(3);
		    var a=(Date.parse(newDate)-Date.parse(now))/3600/1000;
		    var b=(Date.parse(dd)-Date.parse(newDate))/3600/1000;
		    if(a>=0 && b>=0){
		    	
		    }else{
		    	messageShow("只能选择今天到3天内的日期！");
		    	$('#dateQuery').datebox('setValue',now);
		    }
		}
	});
	$('#dateQuery').datebox('setValue',myformatter(date));
	$('#ticketSellDg').datagrid({
		border:true,  
	    fitColumns:true,  //高度适应
	    fit:true,
	    idField:'ticketNum',
	    singleSelect:true,
	    pagination: true,
	    rownumbers:true,//行号  
	    url:'ticketSell?function=query',
	    queryParams:{
	    	date:$('#dateQuery').datebox('getValue'),
	    },
	    toolbar:'#ticketSellTb',
	    columns:[[  
	  	        {field:'ticketNum',title:'编号',width:60,
	  	        	formatter:function(value,row,index){
	  	        		if(row.ticket){
	  	        			return row.ticket.ticketNum;
	  	        		}
	  	        	}},  
	  	        {field:'start_station',title:'起点站',width:90,
	  	        		formatter:function(value,row,index){
	  	        			if(row.ticket){
	  	        				return row.ticket.start_station;
	  	        			}
	  	        		}},
	  	        {field:'end_station',title:'终点站',width:90,
	  	        			formatter:function(value,row,index){
	  	        				if(row.ticket){
	  	        					return row.ticket.end_station;
	  	        				}
	  	        			}},
	  	        {field:'price',title:'价格',width:90,
	  	        				formatter:function(value,row,index){
	  	        					if(row.ticket){
	  	        						return row.ticket.price;
	  	        					}
	  	        				}},
    	  	    {field:'date',title:'乘车日期',width:100},
	  	        {field:'time',title:'出发时间',width:100,
    	  	    	formatter:function(value,row,index){
    	  	    		if(row.ticket){
    	  	    			return row.ticket.time;
    	  	    		}
    	  	    	}},
	  	        {field:'motorcoach_number',title:'汽车编号',width:100,
    	  	    		formatter:function(value,row,index){
    	  	    			if(row.ticket){
    	  	    				return row.ticket.motorcoach_number;
    	  	    			}
    	  	    		}},
	  	        {field:'seatNum',title:'座位数量',width:110,
    	  	    			formatter:function(value,row,index){
    	  	    				if(row.ticket){
    	  	    					return row.ticket.seatNum;
    	  	    				}
    	  	    			}},
	  	        {field:'residueSeat',title:'剩余票数',width:110},
	  	        {field:'opt',title:'操作',width:100,align:'center',
	  	        	 formatter:function(value,rec){  
		 	            	//添加编辑按钮
		 	            	var ticketNum=rec.ticket.ticketNum;
		 	            	var date=rec.date;
		 	                var btn = "<a class='editcls' onclick=\"sell('"+ticketNum+"','"+date+"')\" href='javascript:void(0)'>出售</a>";  
		 	                return btn;  
		 	            } 
	  	        	},
	  	        ]],
	  	      onLoadSuccess:function(data){  
	     	        $('.editcls').linkbutton({text:'出售',plain:true,iconCls:'icon-edit'});  
	     	    }
	});
	setDialog_orderInfo();
	closeDialog_orderInfo();
});
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 

function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

function doSearch(){
	$.ajax({
		async:false,
		cache:false,
		type:'POST',
		dataType:'json',
		url:'ticketSell?function=query',
		data:{
			'start_station':$('#start_stationQuery').val(),
			'end_station':$('#end_stationQuery').val(),
			'date':$('#dateQuery').datebox('getValue')
		},error:function(){
			alert('error');
		},success:function(data){
			$('#ticketSellDg').datagrid('loadData',data);
		}
	});
}

function sell(ticketNum,date){
	$.messager.confirm("提示框", "确定出售吗？", function(r){
		if(r){
			$.ajax({
				async:false,
				cache:false,
				type:'POST',
				dataType:'json',
				url:'ticketSell?function=sell',
				data:{
					'ticketNum':ticketNum,
					'date':date,
					'workerName':$('#workerName').val()
				},error:function(){
					$('#dateQuery').datebox('setValue',date);
					messageShow("出售失败！");
					$('#ticketSellDg').datagrid('reload');
				},success:function(data){
					if(data.data=="fail"){
						messageShow("剩余票数为0，请选择其他车次！");
						$('#dateQuery').datebox('setValue',date);
						$('#ticketSellDg').datagrid('reload',{
							date:$('#dateQuery').datebox('getValue')
						});
					}else{
						messageShow("出售成功！");
						$('#dateQuery').datebox('setValue',date);
						$('#ticketSellDg').datagrid({  
						    url:'ticketSell?function=query',  
						    queryParams:{  
						        date:$('#dateQuery').datebox('getValue'),
						    }  
						});  
						 $('#ticketSellDg').datagrid('clearSelections');
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