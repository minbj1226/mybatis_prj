<%@page import="day1226.EmpDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>크다의 조회</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
})//function
</script>
입력한 연봉보다 많이 받는 사원 검색<br>
<form name="frm" id="frm" action="index.jsp">
<input type="hidden" name="url" value="${param.url}">
<label for="sal">연봉</label>
<input type="text" name="sal"/>
<input type="button" value="검색" class="btn btn-info btn-sm" id="btn"/>
</form>
<div id="output">
<c:if test="${not empty param.sal}">
<%
String sal=request.getParameter("sal");
SelectService2 ss2=SelectService2.getInstance();
List<EmpDomain> empList=ss2.useGreaterThan(Integer.parseInt(sal));

pageContext.setAttribute("empList", empList);
%>
<c:out value="${param.sal}"/>보다 많이 수령하는 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
<th>번호</th>
<th>사원명</th>
<th>연봉</th>
<th>보너스</th>
<th>입사일</th>
</tr>
</thead>
<tbody>
<c:if test="${empty empList}">
<tr>
<td colspan="5" style="text-align: center">
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</td>
</tr>
</c:if>
	<c:forEach var="emp" items="${empList}" varStatus="i">
	<tr>
		<td><c:out value="${i.count }"></c:out>
		<td><c:out value="${emp.ename}"/></td>
		<td><c:out value="${emp.sal}"/></td>
		<td><c:out value="${emp.comm}"/></td>
		<td><fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd EEEE"/></td>
	</c:forEach>
</tbody>
</table>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
</c:if>
</div>