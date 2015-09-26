$(function() {
	$('#centerTab').tabs({
		tools:[{
			iconCls:'icon-back',
			handler: function(){
				$.messager.confirm('注销提示', '你确定注销吗?', function(r){
					if(r){
						window.location = root+'/login/doLogout.jhtml';
					}
				});
			}
		}]
	});
});

/**
 * 创建新选项卡
 * @param tabId    选项卡id
 * @param title    选项卡标题
 * @param url      选项卡远程调用路径
 */
function addTab(tabId,title,url){
	//如果当前id的tab不存在则创建一个tab
	if($("#"+tabId).html()==null){
		var name = 'iframe_'+tabId;
		$('#centerTab').tabs('add',{
			title: title,         
			closable:true,
			cache : false,
			fit:true,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}
}
function navigation(newtitle,url,jd){
	if(url=='workerManage.jsp'){
		if(jd-3<0){
			$('#center_region').panel({ title: '权限不足'});
			document.getElementById("center_iframe").src="jdError.jsp"; 
		}else{
			$('#center_region').panel({ title: newtitle});
			document.getElementById("center_iframe").src=url; 
		}
	}
	if(url=='ticketManage.jsp'){
		if(jd<2){
			$('#center_region').panel({ title: '权限不足'});
			document.getElementById("center_iframe").src='jdError.jsp'; 
		}else{
			$('#center_region').panel({ title: newtitle});
			document.getElementById("center_iframe").src=url; 
		}
	}
	if(url=='ticketSell.jsp'|| url=='ticketReturn.jsp' || url=='ticketGet.jsp'){
		if(jd<1){
			$('#center_region').panel({ title: '权限不足'});
			document.getElementById("center_iframe").src='jdError.jsp'; 
		}else{
			$('#center_region').panel({ title: newtitle});
			document.getElementById("center_iframe").src=url; 
		}
	}
	
	
	
}


