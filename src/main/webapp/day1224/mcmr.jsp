<%@page import="day1224.EmpDTO"%>
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
List<EmpDTO> empList=ss.mcmr(Integer.parseInt(deptno));
pageContext.setAttribute("empList", empList);
%>
<c:out value="${param.deptno}"/>번 부서 사원리스트<br>
<table class="table table-hover">
<thead>
<tr>
<th>번호</th>
<th>사원명</th>
<th>연봉</th>
<th>직무</th>
<th>매니저번호</th>
<th>부서번호</th>
<th>입사일</th>
</tr>
</thead>
<tbody>
<c:if test="${empty empList}">
<tr>
<td colspan="7" style="text-align: center">
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</td>
</tr>
</c:if>
	<c:forEach var="emp" items="${empList}" varStatus="i">
	<tr>
		<td><c:out value="${i.count}"/></td>
		<td><c:out value="${emp.ename}"/></td>
		<td><c:out value="${emp.sal}"/></td>
		<td><c:out value="${emp.job}"/></td>
		<td><c:out value="${emp.mgr}"/></td>
		<td><c:out value="${emp.deptno}"/></td>
		<td><c:out value="${emp.hiredate}"/></td>
	</c:forEach>
</tbody>
</table>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
</c:if>
</div>