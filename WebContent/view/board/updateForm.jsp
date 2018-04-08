<%@ include file="/common/include/inc_head.jsp"%>
<%
		request.setCharacterEncoding("utf-8");
	%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body class="homepage">

	<%@ include file="/common/include/inc_header.jsp"%>

	<div id="page">
		<div class="w3-container">
			
				<p align="center">Modify Board</p>
				<form action="updatePro" method="post" name="writeForm">
					<input type="hidden" name="num" value="${article.num }">
					<input type="hidden" name="pageNum" value="${pageNum }">
					<input type="hidden" name="ref" value="0"> <input
						type="hidden" name="re_step" value="0"> <input
						type="hidden" name="re_level" value="0">
						<input
						type="hidden" name="phone_kind" value="${phone_kind }">

					<table class="w3-table w3-striped w3-border" style="width: 45%;">
						<tr>
							<td><input type="button" value="글목록"  onclick = history.go(-1);></td>
						</tr>
						<tr>
							<td width="30%" align="center">이름</td>
							<td width="100%"><input type="text" name="writer" size="20"
								maxlength="10" value="${article.writer }"></td>
						</tr>
						<tr>
							<td width="30%" align="center">제목</td>
							<td width="100%"><input type="text" name="title" size="100"
								maxlength="110" value="${article.title }"></td>
						</tr>

						<tr>
							<td width="30%" align="center">내용</td>
							<td width="100%"><textarea name="content" rows="13" cols="100" style="resize:none"> 
					${article.content }</textarea></td>
						</tr>

						<tr>
							<td width="30%" align="center">비밀번호</td>
							<td width="100%"><input type="password" name="password"
								size="30" maxlength="40"></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Update"> <input type="reset" value="ReWrite">
								<input type="button" value="viewList"
								onClick='history.go(-1);'></td>
						</tr>

					</table>
				</form>
			
		</div>
	</div>
	<%@ include file="/common/include/inc_footer.jsp"%>
</body>
</html>