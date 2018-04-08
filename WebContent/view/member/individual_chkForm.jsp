<%@ include file="/common/include/inc_head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<body class="homepage">


	<%@ include file="/common/include/inc_header.jsp"%>
	
	<div id="page">

	<div class="w3-container">
		<center><b>비밀번호 확인</b></center>
			<form action="individual_chkpro" method="post" name="checkForm">
			
			<table class="w3-table w3-striped w3-border" width="360">
				<tr height="30">
					<td align="right"><b>Input your Password.</b></td>
				</tr>
				<tr height="30">
					<td align="center">Password : 
						<input type="password" name="password" size="8" maxlength="12">
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="확인">
					</td>
				</tr>

			</table>
		</form>
	</div>
	</div>

</body>
</html>
