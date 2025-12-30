<%@page import="day1230.SelectService4"%>
<%@page import="day1229.SelectService3"%>
<%@page import="day1226.EmpAllDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>$의 사용 조회</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
})//function
</script>
사원 검색<br>
<form id="frm" action="index.jsp">
<input type="hidden" name="url" value="${param.url}"/>
<input type="radio" name="deptno" value="1"/>SI
<input type="radio" name="deptno" value="2"/>SM
<input type="radio" name="deptno" value="3"/>QA
<input type="button" value="검색" class="btn btn-sm btn-info" id="btn"/>
</form>
<div id="output">
<c:if test="${not empty param.deptno}">
<%
String deptno=request.getParameter("deptno");

SelectService4 ss4=SelectService4.getInstance();
List<EmpAllDomain> empList=ss4.dynamicChoose(Integer.parseInt(deptno));

pageContext.setAttribute("empList", empList);
%>
<table class="table table-hover">
<thead>
<tr>
<th>사원 번호</th>
<th>사원명</th>
<th>직무</th>
<th>입사일</th>
<th>연봉</th>
<th>부서번호</th>
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
		<td><fmt:formatDate value="${emp.hiredate}" pattern="yyyy-MM-dd EEEE"/></td>
		<td><c:out value="${emp.sal}"/></td>
		<td><c:out value="${emp.deptno}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
</c:if>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
</div>