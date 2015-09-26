//回车快捷登录 
document.onkeydown=function(event){         
	  var e = event || window.event || arguments.callee.caller.arguments[0]; 
	    if(e && e.keyCode==13){
	    	login()
	    }
	  }
//取消用户名提示
function workerNameVali(){
	if(  $("#workerName").val()!=""&& 2<document.getElementById("workerName").value.length<8){
		document.getElementById('workerNameValidate').innerText=""
	}
	document.getElementById('message').innerText="";
}
//取消密码提示
function passwordVali(){
	if( $("#password").val()!="" && 2<document.getElementById("password").value.length<8){
		document.getElementById('passwordValidate').innerText=""
	}
	document.getElementById('message').innerText="";	
}
function messageVali(){
	
}

//登录
function login(){
	
	//验证用户名不为空
	if($("#workerName").val()==""){
		document.getElementById('workerNameValidate').innerText="请输入用户名";
		clsoeWin();
		return;
	}
	//验证密码不为空
	if($("#password").val()==""){
		document.getElementById('passwordValidate').innerText="请输入密码";
		clsoeWin();
		return;
	}
	//验证长度
	if(document.getElementById('workerName').value.length>8 || document.getElementById('workerName').value.length<2 ){
		document.getElementById('workerNameValidate').innerText="用户名长度在2和8之间";
		clsoeWin();
		return;
	}
	
	if(document.getElementById('password').value.length>8 || document.getElementById('password').value.length<2 ){
		document.getElementById('passwordValidate').innerText="用户名长度在2和10之间";
		clsoeWin();
		return;
	}
	//登录
	$.ajax({
		async:false,
		cache:false,
		type: 'GET',
		dataType : "json",
		data:{
			"workerName":$("#workerName").val(),
			"password":$("#password").val()
		},
		url:"workerManage?function=login",//请求的action路径
		error: function() {
			$.messager.show({
				title:'提示',
				msg:'出错了!',
				showType: 'slide'  
			});
			clsoeWin();
		},
		success:function(data){
			if(data.data=="success"){
				$.messager.show({
					title:'提示',
					msg:'登录成功!',
					showType: 'slide'  
				});
				
				setTimeout('redicet()',800);
			}else{
				document.getElementById('message').innerText="用户名或密码错误";
				clsoeWin();
			}
			
			
		},
	});
}

function redicet(){
	window.location.href='workerManage?function=redirect&workerName='+$("#workerName").val()+'&password='+$("#password").val();
}

function showWin(){
	  /*找到div节点并返回*/  
    var winNode = $("#win");  
    winNode.fadeIn("slow"); 
}
function clsoeWin(){
	var winNode = $("#win");  
	winNode.hide();
}
function Login(){
	showWin();
	login();
}