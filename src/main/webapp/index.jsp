<%@page import="day1224.UrlVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
   content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Astro v5.13.2">
<title>JSP템플릿</title>
<link rel="shortcut icon" href="http://192.168.10.75/mybatis_prj/common/images/favicon.ico">

<script src="http://192.168.10.75/mybatis_prj/common/js/color-modes.js"></script>
<!-- bootstrap CDN 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<meta name="theme-color" content="#712cf9">
<link href="http://192.168.10.75/mybatis_prj/common/css/carousel.css" rel="stylesheet">
<c:import url="http://192.168.10.75/mybatis_prj/fragments/bootstrap_css.jsp"/>
<style type="text/css">
#wrap{  margin: 0px auto; width: 1200px; height: 1000px; }
#left{ width:180px; height:1000px; float:left; }
#right{ width:980px; height:1000px; float:right; }
   
a{color:#333; text-decoration: none}  
a:hover{color:#0000FF; text-decoration: underline}
</style>
<!-- jQuery CDN 시작 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
   
});//ready

</script>


</head>
<body>
   <header data-bs-theme="dark">
   <c:import url="http://192.168.10.75/mybatis_prj/fragments/header.jsp"/>
   </header>
   <main>
      <!-- Marketing messaging and featurettes
  ================================================== -->
      <!-- Wrap the rest of the page in another container to center all the content. -->
      <div class="container marketing">
         <!-- Three columns of text below the carousel -->
         <!-- /.row -->
         <!-- START THE FEATURETTES -->
         <hr class="featurette-divider">
         <div class="row featurette">
         <%
         UrlVo[] urlArr={
        		 new UrlVo("컬럼 하나에 한행 조회", "day1224/scsr"),
        		 new UrlVo("컬럼 하나에 여러행 조회", "day1224/scmr"),
        		 new UrlVo("컬럼 여러개에 한행 조회", "day1224/mcsr"),
        		 new UrlVo("컬럼 여러개에 여러행 조회", "day1224/mcmr"),
        		 new UrlVo("domain 사용", "day1226/domain"),
        		 new UrlVo("like", "day1226/like"),
        		 new UrlVo("작다의 조회", "day1226/lessThan"),
        		 new UrlVo("크다의 조회", "day1226/greaterThan"),
        		 new UrlVo("subquery", "day1226/subquery"),
        		 new UrlVo("union", "day1226/union"),
        		 new UrlVo("join", "day1226/join"),
        		 new UrlVo("subquery & join", "day1229/subqueryNjoin"),
        		 new UrlVo("dollar sign", "day1229/dollar"),
        		 new UrlVo("dynamic query & if", "day1229/dynamicIf"),
        		 new UrlVo("dynamic query & choose", "day1230/dynamicChoose"),
        		 new UrlVo("dynamic query & forEach", "day1230/dynamicForEach"),
        		 new UrlVo("dynamic query & set", "day1230/dynamicSet"),
        		 new UrlVo("transaction", "day1230/transaction"),
        		 new UrlVo(" PL/SQL insert", "day1231/plInsert"),
        		 new UrlVo(" PL/SQL cursor 한행", "day1231/plCursor"),
        		 new UrlVo(" PL/SQL cursor 여러행", "day1231/plCursor2")
         };
         
         pageContext.setAttribute("urlArr", urlArr);
         %>
         	<div id="wrap">
         	<div id="left">
         	<table>
			<c:forEach var="urlArr" items="${urlArr}" varStatus="i">
         	<tr>
			<td><a href="index.jsp?url=${urlArr.url}.jsp"><c:out value="${urlArr.title}"/></a></td>
			</tr>         	
			</c:forEach>
         	</table>
         	</div>
         	<div id="right">
         	<c:if test="${not empty param.url}">
         	<c:import url="${param.url}"/>
         	</c:if>
         	</div>
         	</div>
         </div>
         <hr class="featurette-divider">
         <!-- /END THE FEATURETTES -->
      </div>
      <!-- /.container -->
      <!-- FOOTER -->
      <footer class="container">
         <p class="float-end">
         <c:import url="http://192.168.10.75/mybatis_prj/fragments/footer.jsp"/>
         </p>
      </footer>
   </main>
   
</body>
</html>