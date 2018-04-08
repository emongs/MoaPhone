<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
		request.setCharacterEncoding("utf-8");
	%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<c:if test="${check== 1}">
	<meta http-equiv="Refresh" content="0;url=list?tabid=2&pageNum=${pageNum }&phone_kind=${phone_kind}">
</c:if>

<c:if test="${check!= 1 }">
	<script language="JavaScript">
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>