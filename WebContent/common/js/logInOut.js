
function login(){
	
		//if (document.logFrom.id.value == "" || document.logFrom.pw.value == "")  {
		if ($("#id").val() == "" || $("#pw").val() == "")  {
			alert("ID, PASSWORD 를 입력해 주십시오");
		}
		else {
			$("#loginForm").attr("method","post");
			$("#loginForm").attr("action","../index.jsp");
			$("#loginForm").submit();
		}
	
}



/**
 * 세션 체크
 * 체크 후 로딩 중 레이어 띄움
 */
function checkSession(){
	var check_result = true;
	
	try{
		$.ajax({
		    url : "checkSession.jsp",
		    type : "post",
		    dataType : "text",
		    async : false,
		    error : function( XMLHttpRequest, textStatus, errorThrown ){
		    },
		    success : function( data ){ 
		    	if($.trim(data) != 'T'){
					alert("로그인세션이 만료되었습니다. 로그인 후 사용해 주세요.");
					location.replace("index.jsp");
					check_result = false;
				}
		    }
		});
	}
	catch(error){
	}
	
	if(check_result === false) $('.noneDiv').hide();
	else $('.noneDiv').show();
	
	return check_result;
} 
 