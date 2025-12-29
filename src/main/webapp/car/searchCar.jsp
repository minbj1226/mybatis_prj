<%@page import="kr.co.sist.car.CarService"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>    
<%
String model=request.getParameter("model");
if(model==null) {
	model="K5";
}//end if
out.print(CarService.getInstance().searchCar(model));
%>