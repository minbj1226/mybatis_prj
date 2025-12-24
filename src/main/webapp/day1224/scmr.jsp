<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>컬럼 하나에 여러행 조회</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
})//function
</script>
<form name="frm" id="frm" action="index.jsp">
<input type="hidden" name="url" value="${param.url}">
<label for="deptno">부서번호</label>
<input type="text" name="deptno"/>
<input type="button" value="검색" class="btn btn-info btn-sm" id="btn"/>
</form>
<div id="output">
<c:if test="${not empty param.deptno}">
<%
String deptno=request.getParameter("deptno");
SelectService ss=SelectService.getInstance();
List<String> enameList=ss.scmr(Integer.parseInt(deptno));
pageContext.setAttribute("enameList", enameList);
%>
<c:out value="${param.deptno}"/>번 부서 사원리스트<br>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
<c:if test="${empty enameList}">
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</c:if>
</c:if>
</div>