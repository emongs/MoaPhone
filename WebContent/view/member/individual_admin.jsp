<%@ include file="/common/include/inc_head.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<body class="homepage">

	<script>
	
		function checkIt() {
			var userInput = eval("document.userinput");
	
			if (!userinput.passwd.value) {
				alert("비밀번호를 입력하세요.");
				userinput.passwd.focus();
				return false;
			}
	
			if (userinput.passwd.value != userinput.chkpasswd.value) {
				alert("동일한 비밀번호를 입력하세요.");
				userinput.passwdrepeat.focus();
				return false;
			}
	
			if (!userinput.nickname.value) {
				alert("닉네임을 입력하세요.");
				userinput.nickname.focus();
				return false;
			}
	
			if (!userinput.email.value) {
				alert("E mail을 입력하세요.");
				userinput.email.focus();
				return false;
			}
		}
	</script>
	<%@ include file="/common/include/inc_header.jsp"%>


	<div id="page">
		<div id="w3-container">
			<form action="updateMember" name="userinput" method="post"
				onSubmit="return checkIt()">
				<table class="w3-table-all w3-centered">
					<tr>
						<td colspan="2" align="center"><b>개인정보 수정</b></td>
					</tr>

					<tr>
						<td>아이디</td>
						<td>${mb.id }</td>
					</tr>

					<tr>
						<td>닉네임</td>
						<td><input type="text" name="nickname" id="nickname" value="${mb.nickname }">
						</td>
					</tr>

					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="passwd" id="passwd" value="${mb.passwd}"></td>
					</tr>

					<tr>
						<td>비밀번호 확인</td>
						<td><input type="password" name="chkpasswd" id="chkpasswd">
						</td>
					</tr>

					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" id="email" value="${mb.email }"></td>
					</tr>

					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="등록">
							<button type="button"
								onclick="location.href='/MoaPhone_md2_method_mybatis/index.jsp' ">홈으로</button>
						</td>
					</tr>


				</table>
			</form>
		</div>
	</div>
</body>
</html>