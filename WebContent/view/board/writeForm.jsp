<%@ include file="/common/include/inc_head.jsp"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		request.setCharacterEncoding("utf-8");
	%>

<body class="homepage">
	<%@ include file="/common/include/inc_header.jsp"%>
	<script>
	function checkIt() {
		var writeForm = eval("document.writeForm");
		if(!writeForm.writer.value) {
			alert("작성자를 입력하세요.");
			writeForm.writer.focus();
			return false;
		}
		
		if(!writeForm.title.value) {
			alert("제목을 입력하세요.");
			writeForm.title.focus();
			return false;
		}
		
		if(!writeForm.password.value) {
			alert("비밀번호를 입력하세요.");
			writeForm.password.focus();
			return false;
		}
		
		
		if(!writeForm.content.value) {
			alert("내용을 입력하세요.");
			writeForm.content.focus();
			return false;
		}
		
		
	
	}
	</script>
	
	<div id="page" align="center">

		<div class="w3-container">

			<form action="writePro" method="post"
				name="writeForm" onSubmit="return checkIt()">
				<input type="hidden" name="num" value="${num }"> <input
					type="hidden" name="pageNum" value="${pageNum }"> <input
					type="hidden" name="ref" value="${ref }"> <input
					type="hidden" name="re_step" value="${re_step }"> <input
					type="hidden" name="re_level" value="${re_level }">
					<input type="hidden" name="phone_kind" value="${phone_kind }">

				<p align="center">Write Form</p>
				<table class="w3-table w3-striped w3-border" style="width: 45%;"
					align="center">
					<tr>
						<td align="right" colspan="2">
				<input type="button" value="글목록"  onclick = history.go(-1);>
						</td>
					</tr>
					<tr>
						<td width="30%" align="center">이름</td>
						<td width="70%"><input type="text" id="writer" name="writer" size="20"
							maxlength="20"></td>
					</tr>
					<tr>
						<td width="30%" align="center">제목</td>
						<td width="70%">
							<c:if test="${num==0 }">
							 <input type="text" name="title" id="title" size="100" maxlength="110"> 
							</c:if>
							
							<c:if test="${num!=0 }">
							<input
							type="text" name="title" id="title" size="100" maxlength="100" value="[Re:]">
							</c:if>
						</td>
					</tr>

					<tr>
						<td width="30%" align="center">내용</td>
						<td width="70%"><textarea id="content" name="content" rows="13" cols="100"  style="resize:none"></textarea></td>
					</tr>

					<tr>
						<td width="30%" align="center">비밀번호</td>
						<td width="70%"><input type="password" name="password" size="30"
							maxlength="30"></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Write"> <input type="reset" value="ReWrite">
							<input type="button" value="viewList"
							onClick="window.location='list?&phone_kind=${phone_kind}&tabid=2'"></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
<%@ include file="/common/include/inc_footer.jsp"%>
</body>
</html>
