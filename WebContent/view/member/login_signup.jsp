<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title>Login/Sign-In</title>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" href="../common/css/login_style.css">
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="../common/js/index.js"></script>
</head>

<script type="text/javascript">

	function findId(email,nickname) {
		
		if(email=null){
			url = "../member/findId?email=null&nickname=null";
		} else {
			url = "../member/findId?email="+encodeURIComponent(email) +"&nickname=" +encodeURIComponent(nickname);
		}
	
		open(url, 'confirm', 'toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizeable=yes, width=570, height=750');
	}
	
	function findPassword() {
		url = "../member/findPassword";
	
		open(url, 'confirm', 'toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizeable=yes, width=570, height=750');
	}
	
	



	function checkIt() {
		var userInput = eval("document.userinput");
		if(!userinput.id.value) {
			alert("ID를 입력하세요.");
			userinput.id.focus();
			return false;
		}
		
		if(!userinput.passwd.value) {
			alert("비밀번호를 입력하세요.");
			userinput.passwd.focus();
			return false;
		}
		
		if(userinput.passwd.value != userinput.passwdrepeat.value ) {
			alert("동일한 비밀번호를 입력하세요.");
			userinput.passwdrepeat.focus();
			return false;
		}
		
		if(!userinput.nickname.value) {
			alert("닉네임을 입력하세요.");
			userinput.nickname.focus();
			return false;
		}
		
		if(!userinput.email.value) {
			alert("E mail을 입력하세요.");
			userinput.email.focus();
			return false;
		}
		
		 if(userinput.idchk.value != "Y") {
			alert("id중복확인을 하세요.");
			userinput.idchk.focus();
			return false;
		}
		 
		 if(userinput.nicknamechk.value != "Y") {
				alert("닉네임중복확인을 하세요.");
				userinput.nicknamechk.focus();
				return false;
			}
	}
	
	function openConfirmid(userInput) {
		if(userInput.id.value=="") {
			alert("아이디를 입력하세요.");
			return;
		}
		
		url = "../member/confirmId?id=" + encodeURIComponent(userInput.id.value); // 한글로 넘기기 위해서. 톰캣 8.0부터...
		
		
		open(url, "ID_Check","toolbar=no, location=no, status=no,menubar=no,scrollbars=no,resizeable=no,width=300,height=200");
	}
	
	function openConfirmNickname(userInput) {
		if(userInput.nickname.value=="") {
			alert("닉네임을 입력하세요.");
			return;
		}
		
		url = "../member/confirmNickname?nickname=" + encodeURIComponent(userInput.nickname.value); // 한글로 넘기기 위해서. 톰캣 8.0부터...
		
		open(url, "nickname_Check","toolbar=no, location=no, status=no,menubar=no,scrollbars=no,resizeable=no,width=300,height=200");
	}
</script>

<body>
  <div class="logmod">
  <div class="logmod__wrapper">
    <span class="logmod__close">Close</span>
    <div class="logmod__container">
      <ul class="logmod__tabs">
        <li data-tabtar="lgm-2"><a href="#">로그인</a></li>
        <li data-tabtar="lgm-1"><a href="#">회원가입</a></li>
      </ul>
      <div class="logmod__tab-wrapper">
      <div class="logmod__tab lgm-1">
       
       
        <!-- 회원가입 시작-->
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle"><strong>기본 정보를 입력하세요.</strong></span>
        </div>
        <div class="logmod__form">
          <form action="memberInsert" class="simform" name="userinput" 
                    		onSubmit="return checkIt()">
            <div class="sminputs">
              <div class="input2">
                <label class="string optional" >아이디*</label>
                <input class="string optional" maxlength="255" name="id" id="id" placeholder="아이디" type="text" size="50" />
              	<input type="hidden" name="idchk">
              </div>
              
               <div class="input3">
                <input id="btn"  style="font-size : 15px;" type="button" value="ID중복확인" name="confirm_id"
                	OnClick="openConfirmid(this.form)"/>
              </div>
              <!-- input tag name이랑 id차이 -->
            </div>
            
            <div class="sminputs">
              <div class="input2">
                <label class="string optional" >닉네임*</label>
                <input class="string optional" maxlength="255" name="nickname" id="nickname" placeholder="닉네임" type="text" size="50" />
             	<input type="hidden" name="nicknamechk">
              </div>
              
               <div class="input3">
                 <input id="btn" style="font-size : 13px;" type="button" value="닉네임중복확인" name="confirm_nickname"
                	OnClick="openConfirmNickname(this.form)"/>
              </div>
              
            </div>
            <div class="sminputs">
              <div class="input string optional">
                <label class="string optional" >비밀번호 *</label>
                <input class="string optional" maxlength="255" name="passwd" id="passwd" placeholder="비밀번호" type="text" size="50" />
              </div>
              <div class="input string optional">
                <label class="string optional" >비밀번호 확인 *</label>
                <input class="string optional" maxlength="255" name="passwdrepeat "id="passwdrepeat" placeholder="비밀번호확인" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
            	<div class="input full">
                <label class="string optional" >이메일*</label>
                <input class="string optional" maxlength="255" name="email" id="email" placeholder="이메일" type="text" size="50" />
              </div>

            </div>
            <div class="simform__actions">
              <input class="submit" name="commit" type="submit" value="확인" />
              <span class="simform__actions-sidetext">By creating an account you agree to our <a class="special" href="#" target="_blank" role="link">Terms & Privacy</a></span>
            </div> 
          </form>
        </div> 
        <div class="logmod__alter">
          <div class="logmod__alter-container">
           
              
            <a href="#" class="connect googleplus">
              <div class="connect__icon">
                <i class="fa fa-google-plus"></i>
              </div>
              <div class="connect__context">
                <span>Create an account with <strong>Google+</strong></span>
              </div>
            </a>
          </div>
        </div>
      </div>
      <!-- 회원가입 끝 -->
      
      <!-- 로그인 시작 -->
      <div class="logmod__tab lgm-2">
        <div class="logmod__heading">
          <span class="logmod__heading-subtitle"><strong>아이디와 비밀번호를 입력하세요.</strong></span>
        </div> 
        <div class="logmod__form">
          <form accept-charset="utf-8" action="loginPro" class="simform" name="loginForm" id = "loginForm" method="post">
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-name">아이디*</label>
                <input class="string optional" maxlength="255" name="id" id="id" placeholder="ID" type="text" size="50" />
              </div>
            </div>
            <div class="sminputs">
              <div class="input full">
                <label class="string optional" for="user-passwd">비밀번호 *</label>
                <input class="string optional" maxlength="255" name="passwd" id="passwd" placeholder="Password" type="password" size="50" />
                						<span class="hide-password">Show</span>
              </div>
            </div>
            <div class="simform__actions">
              <input class="submit" name="commit" type="submit" value="Log In" />
              <a class="special" role="link" href="#" OnClick="findId(null,null)">Forgot your ID?</a><br>
              <a class="special" role="link" href="#" OnClick="findPassword()">Forgot your password?</a><br>
            </div> 
          </form>
        </div> 
        <div class="logmod__alter">
          <div class="logmod__alter-container">
           
            <a href="#" class="connect googleplus">
              <div class="connect__icon">
                <i class="fa fa-google-plus"></i>
              </div>
              <div class="connect__context">
                <span>Sign in with <strong>Google+</strong></span>
              </div>
            </a>
          </div>
        </div>
          </div>

            <!-- 로그인 끝-->
      </div>
    </div>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/index.js"></script>

</body>
</html>
