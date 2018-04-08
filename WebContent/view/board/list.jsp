<%@ include file="/common/include/inc_head.jsp"%>
<%
		request.setCharacterEncoding("utf-8");
	%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
a {
	text-decoration: none;
}
</style>
<body class="homepage">


	<%@ include file="/common/include/inc_header.jsp"%>

	<div id="page">
	<c:if test= "${mb==null}">
		<script>
		alert("로그인후 사용하실수 있습니다.");
		openConfirmid();
		history.go(-1);
		</script>
	</c:if>
	


		<p class="w3-left" style="padding-left: 30px"></p>
		<div class="w3-container">
			<span class="w3-center w3-large">
				<h3>${phone_kind } 게시판(전체글 : ${count })</h3>
			</span>
			<!-- <p>
			<a href="writeForm.jsp">글쓰기</a>
		</p> -->
			<c:if test="${count ==0 }">

				<table class="w3-table-all w3-hoverable" border="1"
					style="width: 70%;">
					<tr>
						<td><a href="writeForm?tabid=2&phone_kind=${phone_kind }">글쓰기</a></td>
					</tr>
					<tr class="w3-light-grey">
						<td align="center">게시판에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${count != 0 }">
				<table class="w3-table-all w3-hoverable" style="width: 70%;">
					<tr class="w3-white">
						<td colspan="5"
							style="border: 1px solid #fff !important; border-bottom: 1px solid #ddd !important">
							<input type="button" value="글쓰기"
							onclick="document.location.href='writeForm?tabid=2&phone_kind=${phone_kind}'">
						</td>
					</tr>

					<tr class="w3-light-grey">
						<td align="center" width="50">번호</td>
						<td align="center" width="250">제목</td>
						<td align="center" width="100">작성자</td>
						<td align="center" width="150">작성일</td>
						<td align="center" width="50">조회</td>
					</tr>
					<c:forEach var="article" items="${articleList }">
						<tr height="30">
							<td align="center" width="50">${number }</td>
							<c:set var="number" value="${number-1 }"></c:set>
							<td width="250"><c:if test="${article.re_level > 0 }">
									<img src="/MoaPhone_md2_method/common/images/level.gif"
										width="${article.re_level * 10 }" height="16">
									<img src="/MoaPhone_md2_method/common/images/re.gif">
								</c:if> <c:if test="${article.re_level == 0 }">
									<img src="/MoaPhone_md2_method/common/images/level.gif" height="16">
								</c:if> <a
								href="content?num=${article.num }&pageNum=${currentPage }&tabid=2&phone_kind=${phone_kind }">${article.title }</a>
								<c:if test="${article.readcount >=3 }">
									<img src="/MoaPhone_md2_method/common/images/hot.gif" border="0" height="16">
								</c:if>
							<td align="center" width="100">${article.writer }</td>
							<td align="center" width="150">${article.reg_date }</td>
							<td align="center" width="50">${article.readcount }</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		</div>

		<div class="w3-center">

			<c:if test="${count > 0}">
				<c:if test="${startPage > bottomLine}">
					<a href="list?pageNum=${startPage - bottomLine}&phone_kind=${phone_kind}&tabid=2">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">

					<a href="list?pageNum=${i}&phone_kind=${phone_kind}&tabid=2"> <c:if test="${i != currentPage}">
               [${i}] </c:if> <c:if test="${i == currentPage}">
							<font color='red'>[${i}]</font>
						</c:if></a>
				</c:forEach>
				<c:if test="${endPage < pageCount}">
					<a href="list?pageNum=${startPage + bottomLine}&phone_kind=${phone_kind}&tabid=2">[다음]</a>
				</c:if>
			</c:if>
		</div>
	</div>
<%@ include file="/common/include/inc_footer.jsp"%>
</body>
</html>