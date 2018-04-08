<%@page import="java.util.List"%>
<%@page import="select.Selector"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.PhoneBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	PhoneBean pb = (PhoneBean) request.getSession().getAttribute("pb");
	String sellType = (String) request.getSession().getAttribute("sellType");
	String makeBrand = (String) request.getSession().getAttribute("makeBrand");
	
	Selector sel = new Selector();
	
	String SKTurl = sel.returnURL(pb, sellType, makeBrand, "SKT");
	String LGurl = sel.returnURL(pb, sellType, makeBrand, "LGT");
	String KTurl = sel.returnURL(pb, sellType, makeBrand, "KT");
	
	System.out.println("sk url : " + SKTurl);
	System.out.println("lg url : " + LGurl);
	System.out.println("kt url : " + KTurl);
	
	List<String> skGongsi = sel.getGongsiList(SKTurl);
	System.out.println("sk gongsi size : " + skGongsi.size());
	List<String> lgGongsi = sel.getGongsiList(LGurl);
	System.out.println("lg gongsi size : " + lgGongsi.size());
	List<String> ktGongsi = sel.getGongsiList(KTurl);
	System.out.println("kt gongsi size : " + ktGongsi.size());

	List<String> skSelect = sel.getSelectList(SKTurl);
	System.out.println("sk select size : " + skSelect.size());
	List<String> lgSelect = sel.getSelectList(LGurl);
	System.out.println("lg select size : " + lgSelect.size());
	List<String> ktSelect = sel.getSelectList(KTurl);
	System.out.println("kt select size : " + ktSelect.size());

	List<String> skFare = sel.getFareList(SKTurl);
	System.out.println("sk fare size : " + skFare.size());
	List<String> lgFare = sel.getFareList(LGurl);
	System.out.println("lg fare size : " + lgFare.size());
	List<String> ktFare = sel.getFareList(KTurl);
	System.out.println("kt fare size : " + ktFare.size());
	
	int size = lgFare.size();
	
	System.out.println("size : " + size);
	
%>

<!-- <style type="text/css">

table {
	align : center;
	text-align: center;
	vertical-align: middle;
}	

tr {
	align : center;
	text-align: center;
}

td {
	align : center;
	text-align: center;
}
</style> -->
<br><br><br><br>
<div class = "w3-container">
<table class = "w3-table-all w3-xlarge w3-centered">
	<tr>
		<td rowspan="2">요금제 가격</td>
		<td colspan="2">SKT</td>
		<td colspan="2">LGU</td>
		<td colspan="2">KT</td>
	</tr>
	<tr>
		<td>공시지원금</td>
		<td>선택약정</td>
		<td>공시지원금</td>
		<td>선택약정</td>
		<td>공시지원금</td>
		<td>선택약정</td>
	</tr>
	<% for(int i = 0 ; i < size ; i++) { %>

	<tr>
		<td>
			SK : <%=skFare.get(i) %><br>
			LG : <%=lgFare.get(i) %><br>
			KT : <%=ktFare.get(i) %><br>
		</td>
		<td>월 : <%=skGongsi.get(i) %></td>
		<td>월 : <%=skSelect.get(i) %></td>
		<td>월 : <%=lgGongsi.get(i) %></td>
		<td>월 : <%=lgSelect.get(i) %></td>
		<td>월 : <%=ktGongsi.get(i) %></td>
		<td>월 : <%=ktSelect.get(i) %></td>
	</tr>

	<% } %>	
</table>
</div>
</center>

<%@ include file="/common/include/inc_footer.jsp"%>
</body>

</html>

