<%@page import="model.PhoneBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");	
PhoneBean pb = (PhoneBean) request.getSession().getAttribute("pb");
String sellType = (String) request.getSession().getAttribute("sellType");
String makeBrand = (String) request.getSession().getAttribute("makeBrand");

int idx = pb.getModel_kor().lastIndexOf("_");
if(idx == -1) {
	idx = pb.getModel_kor().length();
}
String phone_kind = pb.getModel_kor().substring(0, idx);
System.out.println("sellType : " + sellType);
System.out.println("makeBrand : " + makeBrand);
%>
<style>
td {
	vertical-align: middle;
	text-align: center;
}



</style>
<center>
<div id = "w3-container">
<a href="<%= request.getContextPath() %>/board/list?tabid=2&phone_kind=<%=phone_kind%>">
<table class = "w3-table-all w3-large w3-centered w3-white" border="2" style="width: 75%">
	<tr>
		<th colspan="30"><br><%=pb.getModel_kor() %><br><br><br></th>
	</tr>
	<tr>
		<%
		if(pb.getPhoto()!=null) {
		%>
		<td width="20%" rowspan="5"><img src="/MoaPhone_md2_method_mybatis/common/images/phone/<%=pb.getPhoto() %>" height="150" width="150"></td>
		<% } else { %>
		<td width="20%" rowspan="5"><img src="/MoaPhone_md2_method_mybatis/common/images/phone/default.jpg" height="150" width="150"></td>
		<% } %>
		<td width="10%"><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/release.png" height="30" width="30"></td>
		<td width="30%"><%=pb.getRelease_date() %></td>
		<td width="10%"><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/os.png" height="30" width="30"></td>
		<td width="30%"><%=pb.getOs() %></td>
	</tr>
	<tr>

		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/standard.png" height="30" width="30"></td>
		<td><%=pb.getStandard() %></td>
		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/battery.png" height="30" width="30"></td>
		<td><%=pb.getBattery() %></td>

	</tr>

	<tr>

		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/cpu.png" height="30" width="30"></td>
		<td><%=pb.getCpu() %></td>
		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/memory.png" height="30" width="30"></td>
		<td><%=pb.getMemory() %></td>

	</tr>

	<tr>

		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/display.png" height="30" width="30"></td>
		<td><%=pb.getDisplay() %></td>
		<td><img src="/MoaPhone_md2_method_mybatis/common/images/specIcon/camera.png" height="30" width="30"></td>
		<td><%=pb.getCamera() %></td>

	</tr>

	
</table>
</a>
</div>
<jsp:include page="test.jsp" />
