<%@ include file="/common/include/inc_head.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
		request.setCharacterEncoding("utf-8");
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="homepage">

	<%@ include file="/common/include/inc_header.jsp"%>
	
	<div id="page">

<div class = "container">
	<table class="w3-table-all" style ="width:80%;" align="center">
		<tr>
			<td colspan="4" style="border: 1px solid #fff !important; border-bottom: 1px solid #ddd !important">
			<b>글 내용 보기</b>
			</td>
		</tr>
		<tr height="30">
			<td width = "125" align = "center">글 번호  </td>
			<td width = "125" align = "center">${article.num }</td>
			<td width = "125" >조회수</td>
			<td width = "125" align = "center">${article.readcount }</td>
		</tr>
		
		<tr height ="30">
			<td width = "125" align = "center">작성자 </td>
			<td width = "125" align = "center">${article.writer }</td>
			<td width = "125" >작성일</td>
			<td width = "125" align = "center">${article.reg_date }</td>
		</tr>
		
		<tr height ="30">
			<td width = "125" align = "center">글제목 </td>
			<td width = "125" align = "center" colspan="3">${article.title }</td>
		</tr>
		
		<tr height ="30">
			<td width = "125" align = "center">글 내용 </td>
			<td width = "125" align = "center" colspan="3">${article.content }</td>
		</tr>
		
		<tr height = "30">
			<td colspan="4" class = "w3-center">
				<input type = "button"  value = "글 수정" onclick = "document.location.href='updateForm?num=${article.num }&pageNum=${pageNum }&tabid=2&phone_kind=${article.phone_kind}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type = "button"  value = "글 삭제" onclick = "document.location.href='deleteForm?num=${article.num }&pageNum=${pageNum }&tabid=2&phone_kind=${article.phone_kind}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type = "button"  value = "답글쓰기" onclick = "document.location.href='writeForm?num=${num }&tabid=2&ref=${ref }&re_step=${re_step }&re_level=${re_level }&pageNum=${pageNum }&phone_kind=${article.phone_kind}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type = "button"  value = "글 목록" onclick = "document.location.href='list?pageNum=${pageNum }&tabid=2&phone_kind=${article.phone_kind}'">
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		</table>
		
</div>
</div>
<%@ include file="/common/include/inc_footer.jsp"%>
</body>
</html>