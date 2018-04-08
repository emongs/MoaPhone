<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 중복확인</title>
	<script type="text/javascript">
			function setId() {
				opener.document.userinput.id.value="${id}";
				opener.document.userinput.idchk.value="Y";
				self.close();
			}
	</script>
</head>
<body>
	<c:if test="${chk==1 }">
	<table width="270" border="0" cellpadding="5" cellspacing="0">
	<tr>
		<td height="40">${id}은/는 이미 사용중인 아이디입니다.</td>
	</tr>	
</table>

	<form name="checkForm" method="post" action="confirmId">
		<table width="270" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td align="center"> <p>다른 아이디를 선택하세요. </p>
				<input type="text" name="id" size="10" maxlength="12">
				<input type="submit" value="중복확인">
				</td>
			</tr>
		</table>
	</form>
	</c:if>
	<c:if test = "${chk!=1 }">
		<c:if test = '${id.equals("")}'>
	
		<form name="checkForm" method="post" action="confirmId">
		<table width="270" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td align="center"> <p>ID를 입력하세요. </p>
				<input type="text" name="id" size="10" maxlength="12">
				<input type="submit" value="중복확인">
				</td>
			</tr>
		</table>
	</form>
		</c:if>
		<c:if test= '${!id.equals("")}'>
	
		<table width="270" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td align="center"> <p>입력하신 ${id } 는 사용하실 수 있는 ID입니다. </p>
				<input type="button" value="닫기" onclick="setId()">
				</td>
			</tr>
		</table>
		</c:if>
	</c:if>
</body>
</html>