<%@page import="day1226.EmpAllDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>유니온 조회</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
})//function
</script>
본사 지사의 모든 사원 검색<br>
<div id="output">
<%
SelectService2 ss2=SelectService2.getInstance();
List<EmpAllDomain> empList=ss2.useUnion();

pageContext.setAttribute("empList", empList);
%>
<table class="table table-hover">
<thead>
<tr>
<th>사원 번호</th>
<th>사원명</th>
<th>직무</th>
<th>매니저번호</th>
<th>입사일</th>
<th>연봉</th>
<th>부서번호</th>
<th>타입</th>
</tr>
</thead>
<tbody>
<c:if test="${empty empList}">
<tr>
<td colspan="8" style="text-align: center">
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</td>
</tr>
</c:if>
	<c:forEach var="emp" items="${empList}" varStatus="i">
	<tr>
		<td><c:out value="${emp.empno }"></c:out>
		<td><c:out value="${emp.ename}"/></td>
		<td><c:out value="${emp.job}"/></td>
		<td><c:out value="${emp.mgr}"/></td>
		<td><fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><c:out value="${emp.sal}"/></td>
		<td><c:out value="${emp.deptno}"/></td>
		<td><c:out value="${emp.officeType}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
</div>