<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.dream.www.exam.dto.KnowledgeDto" %>
<%@ page import="java.util.List" %>
<%
	List<KnowledgeDto> data = (List<KnowledgeDto>)request.getAttribute ("data");
	for (KnowledgeDto k : data) {
%>
	<option value="<%=k.getId()%>"><%=k.getName()%></option>
<%
	}
%>