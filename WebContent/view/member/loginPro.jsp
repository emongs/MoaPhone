<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${mb!=null }">
	<script>
		function wclose() {
			window.opener.top.location.href = "../index"
			window.opener.top.location.reload(); //새로고침
			self.close();
		}
	
		wclose();
		alert('<%=session.getAttribute("nickname")%>님 환영합니다!');
	</script>
</c:if>

<c:if test="${mb==null}">
	<script>
		alert("로그인에 실패하였습니다.");
		history.go(-1);
	</script>
</c:if>

