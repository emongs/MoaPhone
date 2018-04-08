<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 찾기</title>
<script type="text/javascript">
	function findId() {
		self.close();
	}
</script>
</head>
<body>
	<c:if test="${findid ne '' }"> <!-- findid로 바꿔줍시다 -->
		<table width="270" border="0" cellpadding="5" cellspacing="0">
				<tr>
					<td align="center">
						<p>찾으시는 ID는 ${findid } 입니다.</p> 
						<input type="button" value="닫기" onclick="findId()">
					</td>
				</tr>
		</table>

		
	</c:if>
	<c:if test="${findid eq '' }">
		<c:if test="${email.equals('') || nickname.equals('')}">

			<p>이메일, 닉네임을 모두 입력하세요.</p> 
			<form name="checkForm" method="post" action="findId">
				<table width="270" border="0" cellpadding="5" cellspacing="0">
					<tr>
						<td align="center">
							이메일 : <input type="text" name="email" id="email" size="10" maxlength="12"> 
						</td>
					</tr>
					
					<tr>
						<td align="center">
							닉네임 : <input type="text" name="nickname" id="nickname" size="10" maxlength="12"> 
						</td>
					</tr>
					
					<tr>
						<td align ="center">
							<input type = "submit" value="아이디 찾기">
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${!email.equals('') && !nickname.equals('')}">

			<p align="center">해당 정보와 일치하는 ID가 없습니다. 다시 입력해주세요.</p> 
			<form name="checkForm" method="post" action="findId">
				<table width="270" border="0" cellpadding="5" cellspacing="0">
					<tr>
						<td align="center">
							이메일 : <input type="text" name="email" id="email" size="10" maxlength="12"> 
						</td>
					</tr>
					
					<tr>
						<td align="center">
							닉네임 : <input type="text" name="nickname" id="nickname" size="10" maxlength="12"> 
						</td>
					</tr>
					
					<tr>
						<td align ="center">
							<input type = "submit" value="아이디 찾기">
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	</c:if>
</body>
</html>