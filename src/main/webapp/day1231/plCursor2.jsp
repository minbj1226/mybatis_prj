<%@page import="day1231.MemberDomain"%>
<%@page import="day1231.SelectService5"%>
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
<h2>procedure를 사용한 select</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
	
	$("#flag").click(function(){
		var flagChk=$("#flag").prop("checked");
		$("input[name='empno']").prop("checked", flagChk);	
	})
	
	<c:forEach var="empno" items="${paramValues.empno}">
		$("[value='${empno}']").prop("checked", true);
	</c:forEach>
	
})//function
</script>
전체 검색<br>
<div id="output">
<%
SelectService5 ss5=SelectService5.getInstance();

List<MemberDomain> md=ss5.searchAllMember();

pageContext.setAttribute("md", md);
%>
<table class="table table-hover">
<thead>
<tr>
<th>번호</th>
<th>이름</th>
<th>나이</th>
<th>성별</th>
<th>전화번호</th>
<th>가입일</th>
</tr>
</thead>
<tbody>
<c:if test="${empty md}">
<tr>
<td colspan="6">회원정보가 존재하지 않습니다.</td>
</tr>
</c:if>
<c:forEach var="md" items="${md}">
<tr>
<td><c:out value="${md.num}"/></td>
<td><c:out value="${md.name}"/></td>
<td><c:out value="${md.age}"/></td>
<td><c:out value="${md.gender}"/></td>
<td><c:out value="${md.tel}"/></td>
<td>Timestamp<fmt:formatDate value="${md.inputDate}" pattern="yyyy-MM-dd EEEE kk:mm:ss"/>
/ Date <fmt:formatDate value="${md.input_date}" pattern="yyyy-MM-dd EEEE kk:mm:ss"/></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>