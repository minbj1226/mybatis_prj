<%@page import="day1226.CarModelDomain"%>
<%@page import="day1226.EmpAllDomain"%>
<%@page import="day1226.SelectService2"%>
<%@page import="day1224.EmpDTO"%>
<%@page import="java.util.List"%>
<%@page import="day1224.SelectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>join 조회</h2>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
	})//click
})//function
</script>
보유중인 모든 차량 검색<br>
<div id="output">
<%
SelectService2 ss2=SelectService2.getInstance();
List<CarModelDomain> carList=ss2.useJoin();

pageContext.setAttribute("carList", carList);
%>
<table class="table table-hover">
<thead>
<tr>
<th>이미지</th>
<th>제조사</th>
<th>모델명</th>
<th>연식</th>
<th>가격</th>
<th>배기량</th>
<th>입력일</th>
</tr>
</thead>
<tbody>
<c:if test="${empty carList}">
<tr>
<td colspan="7" style="text-align: center">
<img src="images/na.jpg" style="width:300px; height:260px;"/>
</td>
</tr>
</c:if>
	<c:forEach var="car" items="${carList}" varStatus="i">
	<tr>
		<td><img src="day1226/car_img/${car.car_img }" style="width:80px; height:60px"></td>
		<td><c:out value="${car.maker}"/></td>
		<td><c:out value="${car.model}"/></td>
		<td><c:out value="${car.car_year}"/></td>
		<td><fmt:formatNumber value="${car.price}" pattern="#,###"/></td>
		<td><c:out value="${car.cc}"/></td>
		<td><fmt:formatDate value="${car.input_date}" pattern="yyyy-MM-dd EEEE"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
<c:forEach var="ename" items="${enameList}" varStatus="i">
<c:out value="${i.count}"/><input type="checkbox" name="ename" value="${ename}"/>
<c:out value="${ename}"/>
</c:forEach>
</div>