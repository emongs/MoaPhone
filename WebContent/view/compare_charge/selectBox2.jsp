<%@ include file="/common/include/inc_head.jsp"%>
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
	var httpRequest = null;
	var res = new Array();
	var idx = 0;
	var name= "";
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

	function getText(name2, value) {
		name=name2;
		alert(name + ":" + value);
		divid = document.getElementById(name);
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
		} else if (name == "gu") {
			gu = value;
			params = "si=" + encodeURIComponent(si)
			+ "&gu=" + encodeURIComponent(value);
			divid = document.getElementById("dong");

		} else if (name == "dong") {
			params = "si=" + encodeURIComponent(si)
			+ "&gu=" + encodeURIComponent(gu) + "&dong=" + encodeURIComponent(value);

		} else {
			return;
		}
		alert("====" + params);
		sendRequest("/MoaPhone_md2_method_mybatis/view/compare_charge/selector2.jsp", params, returnText, "GET");
	}
	function returnText() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				alert(httpRequest.responseText);
				alert(name);
				if(name=="si"){
				divid.innerHTML = httpRequest.responseText;
								
				} else if (name=="gu") {
					divid.innerHTML = httpRequest.responseText;
				} else if (name=="dong") {
						document.getElementById("kkk").innerHTML=httpRequest.responseText;
				}
			}
		}
	}

</script>

<body class="homepage">


	<%@ include file="/common/include/inc_header.jsp"%>

	<%
		request.setCharacterEncoding("utf-8");
	%>

	<div id="page">


		<div id="outer" align="center"
			style="display: inline-block; margin: 20px;">
			<div id="inner" align="center"
				style="display: inline-block; margin: 20px;">
				<div id="si" align="center"
					style="display: inline-block; margin: 20px;"><jsp:include
						page="selector2.jsp" /></div>
				<div id="gu" align="center"
					style="display: inline-block; margin: 20px;">
					<select name="gu">
						<option value="핸드폰을 선택하세요">핸드폰을 선택하세요.</option>
					</select>
				</div>
				<div id="dong" align="center"
					style="display: inline-block; margin: 20px;">
					<select name="dong">
						<option value="selectBox2====할부개월을 선택하세요">selectBox2할부개월을 선택하세요.</option>
					</select>
				</div>
			</div>
		</div>
		
		<div id="kkk">
		
		
		
		</div>
</body>
</html>