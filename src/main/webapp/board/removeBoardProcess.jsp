<%@ page import="kr.co.sist.board.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bDTO" class="kr.co.sist.board.BoardDTO" scope="page"/>
<jsp:setProperty name="bDTO" property="*"/>
<%

bDTO.setIp(request.getRemoteAddr());

BoardService bs=BoardService.getInstance();
boolean flag=bs.removeBoard(bDTO);
pageContext.setAttribute("flag", flag);
%>

<script type="text/javascript">
<c:choose>
    <c:when test="${flag}">
            alert("글 삭제 성공.");
            location.href="boardList.jsp?currentPage=${param.currentPage}";
	</c:when>

    <c:otherwise>
            alert("글 삭제중 문제가 발생했습니다");
            history.back();
    </c:otherwise>
</c:choose>
</script>