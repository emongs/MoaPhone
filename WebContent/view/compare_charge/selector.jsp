<%@page import="model.PhoneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="select.Selector"%>

<%
	request.setCharacterEncoding("UTF-8");
	PhoneBean pb = new PhoneBean();
	String si = null;
	String gu = null;
	String dong = null;
	String name = "si";
	String nameset = "제조사를 선택하세요";
	Selector sel = new Selector();
	List li = null;

	System.out.println("aaaaaa:" + request.getParameter("si"));
	int index = 0;

//////////////////////////////////////////////////////////////////////////////	
	if (request.getParameter("si") == null) {
		li = sel.selectPhone(si, gu);
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
//////////////////////////////////////////////////////////////////////////////////
	if (request.getParameter("si") != null && request.getParameter("gu") == null) {
		name = "gu";
		nameset = "핸드폰 모델을 선택하세요.";
		request.getSession().removeAttribute("pb");
		si = request.getParameter("si");
		System.out.println("si : " + si);
		li = sel.selectPhone(si, gu);
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
//////////////////////////////////////////////////////////////////////////////////
	}
	if (request.getParameter("gu") != null && request.getParameter("dong") == null) {
		name = "dong";
		nameset = "할부개월을 선택하세요";
		si = request.getParameter("si");
		gu = request.getParameter("gu");
		System.out.println("gu : " + gu);
		li = sel.selectPhone(si, gu);
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
/////////////////////////////////////////////////////////////////////////////////////
	}
	if (request.getParameter("dong") != null) {
		name = "showPrice";
		si = request.getParameter("si");
		gu = request.getParameter("gu");
		dong = request.getParameter("dong");
		pb = sel.showPrice(si, gu, dong);
		request.getSession().setAttribute("pb", pb);
		request.getSession().setAttribute("sellType", dong);
		request.getSession().setAttribute("makeBrand", si);
		//Selector.java에 무언가를 만들어서 이거를 실행시켜줘야함.
		// method(si,gu,dong)
%>
<%-- <div id="<%=name%>" align="center">
 --%>
<p align="center">선택하신 <%=gu %> 의 통신사별 기기값은 <br>
SK : <%=pb.getPrice_sk() %>, KT : <%=pb.getPrice_kt() %>, LG : <%=pb.getPrice_lg() %> 입니다.
</p>
<jsp:include page="showPhoneInfo.jsp" />
<!-- </div> -->
<%
	}
%>


