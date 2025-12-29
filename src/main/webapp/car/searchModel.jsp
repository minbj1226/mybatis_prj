<%@page import="kr.co.sist.car.CarService"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>    
<%
String maker=request.getParameter("maker");
if(maker==null) {
	maker="현대";
}//end if
out.print(CarService.getInstance().searchModel(maker));
%>