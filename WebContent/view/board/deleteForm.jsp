<%@ include file="/common/include/inc_head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
		request.setCharacterEncoding("utf-8");
	%>
<body class="homepage">


	<%@ include file="/common/include/inc_header.jsp"%>
	
	<div id="page">

	<div class="w3-container">
		<center><b>Delete Board</b></center>
			<form action="deletePro?tabid=2&pageNum=${pageNum }&phone_kind=${phone_kind}" method="post" name="delForm">
			
			<table class="w3-table w3-striped w3-border" width="360">
				<tr height="30">
					<td align="right"><b>Input your Password.</b></td>
				</tr>
				<tr height="30">
					<td align="center">Password : 
						<input type="password" name="password" size="8" maxlength="12">
						<input type="hidden" name="num" value="${num }">
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Delete">
						<input type="button" value="viewList" onClick="window.location='list?tabid=2&phone_kind=${phone_kind}'">

					</td>
				</tr>

			</table>
		</form>
	</div>
	</div>
<%@ include file="/common/include/inc_footer.jsp"%>
</body>
</html>
