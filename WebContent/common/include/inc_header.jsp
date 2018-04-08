<!-- Header -->

<%@page import="model.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	MemberBean mb = (MemberBean) session.getAttribute("mb");
	String memberId = null;
	String memberNickname = null;
	if(mb!=null) {
		memberId = mb.getId();
		memberNickname = mb.getNickname();
	}
	
	String tabid = request.getParameter("tabid");
	if(tabid == null) {
		tabid ="1";
	}
	boolean login = memberId == null ? false : true;
%>
<style>
.mySlides {display:none;}
</style>


<script>
	function openConfirmid() {
		url = "<%=request.getContextPath()%>/member/login_signup";

		open(url, 'confirm', 'toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizeable=yes, width=570, height=750');
	}
	
	var myIndex = 0;
	carousel();
	
	function carousel() {
	    var i;
	    var x = document.getElementsByClassName("mySlides");
	    for (i = 0; i < x.length; i++) {
	       x[i].style.display = "none";  
	    }
	    myIndex++;
	    if (myIndex > x.length) {myIndex = 1}    
	    x[myIndex-1].style.display = "block";  
	    setTimeout(carousel, 3000); // Change image every 2 seconds
	}
</script>


<div id="header">
	<div class = "info">
	<%
		if(login) {
	%>
		<p align="right" style="font-size:15px;"> ${mb.id }(${mb.nickname })님 환영합니다.
		&nbsp;&nbsp;&nbsp; <a href="<%= request.getContextPath() %>/member/logout">로그아웃</a>
		</p>
	<%
		} 
	%>
	</div>
	<div class="container">
		
		<!-- Logo -->
		<div id="logo" style="margin-top:-15px;">
			<h1>
				<a href="<%= request.getContextPath() %>/view/compare_charge/selectBox.jsp">MOA_PHONE</a>
			</h1>
		</div>


		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li <%=tabid.equals("1") ? "class='active'" : ""%>><a href="/MoaPhone_md2_method_mybatis/view/compare_charge/selectBox.jsp">요금비교</a></li>
				<li <%=tabid.equals("2") ? "class='active'" : ""%>><a href="<%= request.getContextPath() %>/board/list?tabid=2">핸드폰 게시판</a></li>
				<li <%=tabid.equals("3") ? "class='active'" : ""%>><a href="/MoaPhone_md2_method_mybatis/view/3support/3support.jsp?tabid=3">3사 공시지원금</a></li>
				<%
					if(login) {
				%>
						<li <%=tabid.equals("4") ? "class='active'" : ""%>><a href="<%= request.getContextPath() %>/member/individual_chkForm?tabid=4">내 정보</a></li>
						<%-- <li><a href="<%= request.getContextPath() %>/member/logout.jsp">로그아웃</a></li> --%>
				<% } else { %>
				<li><a href="#" OnClick="openConfirmid()">로그인</a></li>
				<%  } %> 
			</ul>
		</nav>

	</div>
</div>
<!-- Header -->
		<center>
		<div id = "container">
			<div class="w3-content w3-section" style="max-width:75% ; ">
			  <img class="mySlides" src="/MoaPhone_md2_method_mybatis/common/images/ss.png" style="width:75%;height:400px">
			  <img class="mySlides" src="/MoaPhone_md2_method_mybatis/common/images/apple.png" style="width:75%;height:400px">
			  <img class="mySlides" src="/MoaPhone_md2_method_mybatis/common/images/lg.jpg" style="width:75%;height:400px">
			</div>
		</div>
		</center>
	<!-- <div id="banner" style="height : 500px ;"> -->
		
	</div>
	
	<!--  배너에 파도치는 그림 -->