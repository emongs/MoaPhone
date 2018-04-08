<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String SESS_ID  = request.getParameter("id");
	String SESS_NICKNAME = request.getParameter("nickname");
	 
	System.out.println("SESS_USER_SEQ ------------>"+ SESS_ID);
	System.out.println("SESS_USER_SEQ ------------>"+ SESS_NICKNAME  );
%>