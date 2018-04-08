<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="select.Selector"%>

<%
	String si = null;
	String gu = null;
	String dong = null;
	String name = "si";
	String nameset = "제조사를 선택하세요.";
	System.out.println("aaaaaa:" + request.getParameter("si"));
	Selector sel = new Selector();

	int index = 0;
	if (request.getParameter("dong") != null) {

		System.out.println("last params  si : " + si + " , gu : " + gu
				+ " , dongff : " + request.getParameter("dong"));
%>
<%
	} else if (request.getParameter("gu") != null) {
		name = "dong";
		nameset = "select2.jsp 111할부개월을 선택하세요.";
		gu = request.getParameter("gu");
		System.out.println("gu : " + gu);
		List li = sel.selectPhone(si, gu);
%>
	<select name="<%=name%>" onchange="getText('<%=name%>', this.value)">
		<option value="<%=nameset%>"><%=nameset%></option>
		<%
			for (int i = 1; i < li.size(); i++) {
					out.print("<option value='" + li.get(i) + "'>" + li.get(i)
							+ "</option>");
				}
		%>
	</select>
	<%
		} else if (request.getParameter("si") != null) {
	
			name = "gu";
			nameset = "핸드폰 모델을 선택하세요.";
			si = request.getParameter("si");
			System.out.println("si : " + si);
			List li = sel.selectPhone(si, gu);
	%>
	<select name="<%=name%>" onchange="getText('<%=name%>', this.value)">
		<option value="<%=nameset%>"><%=nameset%></option>
		<%
			for (int i = 1; i < li.size(); i++) {
					out.print("<option value='" + li.get(i) + "'>" + li.get(i)
							+ "</option>");
				}
		%>
	</select>
	<%
		} else {
			List li = sel.selectPhone(si, gu);
	%>
	
	<select name="<%=name%>" onchange="getText('<%=name%>', this.value)">
		<option value="<%=nameset%>"><%=nameset%></option>
		<%
			for (int i = 1; i < li.size(); i++) {
					out.print("<option value='" + li.get(i) + "'>" + li.get(i)
							+ "</option>");
				}
		%>
	</select>
<%
	}
%>


