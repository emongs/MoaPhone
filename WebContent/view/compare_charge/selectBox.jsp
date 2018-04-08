<%@ include file="/common/include/inc_head.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@page import="java.util.List"%>
<%@page import="select.Selector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
a {
	text-decoration: none;
}

#outer {
	width: 100%;
}

#inner {
	width: 75%;
	margin: 0 auto;
}
</style>

<script type="text/javascript">
	var divid;
	var si;
	var gu;
	var dong;
	var httpRequest = null;
	var res = new Array();
	var idx = 0;
	function sendRequest(url, params, callback, method) {
		httpRequest = new XMLHttpRequest();
		var httpMethod = method ? method : 'GET';
		if (httpMethod != 'GET' && httpMethod != 'POST') {
			httpMethod = 'GET';
		}
		var httpParams = (params == null || params == '') ? null : params;
		var httpUrl = url;
		if (httpMethod == 'GET' && httpParams != null) {
			httpUrl = httpUrl + "?" + httpParams;
		}
		httpRequest.open(httpMethod, httpUrl, true);
		httpRequest.setRequestHeader(
			'Content-Type', 'application/x-www-form-urlencoded');
		httpRequest.onreadystatechange = callback;
		alert(httpUrl);
		httpRequest.send(httpMethod == 'POST'
			? httpParams : null);
	}

	function getText(name, value) {
		alert(value);
		divid = document.getElementById(name);
		// 파라미터로 넘어온 name에 해당하는 id를 찾아서 
		// 그 id에 담겨있는 값을 divid에 저장
		alert("divid : " + divid);
		alert("getText param name : " + name);
		if (value == "삼성") {
			value = "ss";
		}
		res[idx++] = value;
		alert("res : " + res);
		alert("getText param value : " + value);
		var params = "";
		if (name == "si") {
			si = value;
			params = "si=" + encodeURIComponent(value);
			divid = document.getElementById("gu");
			alert("selectBox first if divid(document.getelebyid(gu) : " + divid);
		} else if (name == "gu") {
			gu = value;
			params = "si=" + encodeURIComponent(si)
			+ "&gu=" + encodeURIComponent(value);
			divid = document.getElementById("dong");
			alert("selectBox first else if divid(document.getelebyid(dong) : " + divid);
		} else {
			dong = value;
			params = "si=" + encodeURIComponent(si) 
				+ "&gu=" + encodeURIComponent(gu) 
				+ "&dong=" + encodeURIComponent(dong);
			divid = document.getElementById("showPrice");
				// 만약 새로운 결과값을 보여주고싶다면 
				// div id가 ~~~ 인 곳으로 divid getElement 해주기.
		}
		alert("====" + params);
		sendRequest("/MoaPhone_md2_method_mybatis/view/compare_charge/selector.jsp", params, returnText, "GET");
	}
	function returnText() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				alert(httpRequest.responseText);
				divid.innerHTML = httpRequest.responseText;
			}
		}
	}
</script>


<body class="homepage">


	<%@ include file="/common/include/inc_header.jsp"%>

	

	<div id="page">
			<div id="outer" align="center" style="display: inline-block; margin: 20px;">
				<div id="inner" align="center" style="display: inline-block; margin: 20px;">
           			<!-- <form action="selectBox.jsp" method="post"> -->					
           			<div id="si" align="center" style="display: inline-block; margin: 20px;">
           			<jsp:include page="selector.jsp" /></div>
					<div id="gu" align="center" style="display: inline-block; margin: 20px;">
						<select name="gu">
							<option value="핸드폰을 선택하세요">핸드폰을 선택하세요.</option>
						</select>
					</div>
					
					<div id="dong" align="center" style="display: inline-block; margin: 20px;">
						<sel 	ect name="dong">
							<option value="할부개월을 선택하세요.">할부개월을 선택하세요.</option>
						</select>
					</div>
					<!-- <input type = "submit" value="조회">
					</form> -->
				</div>
			</div>
			
			<div id="showPrice">
			
			</div>
			
		<%-- si : <%=request.getParameter("si")%>
		gu : <%=request.getParameter("gu")%>
		dong : <%=request.getParameter("dong")%> --%>
		<%@ include file="/common/include/inc_footer.jsp"%>	
