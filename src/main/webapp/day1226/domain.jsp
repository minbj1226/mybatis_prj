<%@page import="day1226.EmpDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>DTO와 Domain의 사용</h2>
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
<label for="empno">사원번호</label>
<input type="text" name="empno"/>
<input type="button" value="검색" class="btn btn-info btn-sm" id="btn"/>
</form>
<div id="output">
<c:if test="${not empty param.deptno and not empty param.empno}">
<jsp:useBean id="empDTO" class="day1226.EmpDTO" scope="page"/>
<jsp:setProperty property="*" name="empDTO"/>
<%
SelectService2 ss2=SelectService2.getInstance();
EmpDomain empDomain=ss2.useDomain(empDTO);

pageContext.setAttribute("empDomain", empDomain);
%>
<c:out value="${param.deptno} (${param.empno})"/>사원정보 조회 결과<br>
<c:choose>
<c:when test="${not empty empDomain}">
사원명: <c:out value="${empDomain.ename}"/><br>
연봉: $<c:out value="${empDomain.sal}"/><br>
보너스: $<c:out value="${empDomain.comm}"/><br>
입사일: <fmt:formatDate value="${empDomain.hiredate}" pattern="yyyy-MM-dd kk:mm"/><br>
</c:when>
<c:otherwise>
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</c:otherwise>
</c:choose>
</c:if>
</div>