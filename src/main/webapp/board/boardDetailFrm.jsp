<%@page import="kr.co.sist.board.BoardDTO"%>
<%@page import="kr.co.sist.board.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
<%
String num=request.getParameter("num");
if(num!=null) { //입력된 번호가 있다면
	BoardService bs=BoardService.getInstance();
	try {
		int tempNum=Integer.parseInt(num);
		Object obj=session.getAttribute(String.valueOf(tempNum));
		if(obj==null) {
			bs.modifyBoardCnt(tempNum); //세션에 값이 없을때만 카운트를 올림
		}
		
		BoardDTO bDTO=bs.searchOneBoard(Integer.parseInt(num));
		
		//해당 번호의 글을 읽었음을 저장
		session.setAttribute(String.valueOf(tempNum), true);
		
		pageContext.setAttribute("bDTO", bDTO);
	} catch(NumberFormatException nfe) {
	}
}
%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="auto">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Astro v5.13.2">
<title>게시판 글 읽기</title>
<link rel="shortcut icon"
	href="${CommonURL}/common/images/favicon.ico">

<script src="${CommonURL}/common/js/color-modes.js"></script>
<!-- bootstrap CDN 시작 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>

<meta name="theme-color" content="#712cf9">
<link href="${CommonURL}/common/css/carousel.css"
	rel="stylesheet">
<jsp:include page="../fragments/bootstrap_css.jsp"></jsp:include>
<style type="text/css">
#wrap {
	margin: 0px auto;
	width: 1200px;
	height: 1000px;
}

#header {
	height: 150px;
}

#container {
	height: 700px;
}

#footer {
	height: 150px;
}
</style>
<!-- jQuery CDN 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css"
	rel="stylesheet" />
	
<c:if test="${sessionScope.userId == null }">
	<c:redirect url="${CommonURL }/login/loginFrm.jsp" />
</c:if>
<script type="text/javascript">
	$(function() {
		$('#content').summernote(
				{
					tabsize : 2,
					height : 300,
					width : 500,
					toolbar : [ [ 'style', [ 'bold', 'italic', 'clear' ] ],
							[ 'fontsize', [ 'fontsize' ] ],
							[ 'color', [ 'color' ] ],
							[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
							[ 'insert', [ 'link', 'picture' ] ] ]
				});
	});//ready
</script>
<script type="text/javascript">
	$(function() {
		$("#btnModify").click(function() {

			if(confirm("글을 정말 수정하시겠습니까?")) {
			}
			//제목과 내용에 값이 있는지 유효성 검증
			if ($("#content").val() == "" || $("#content").val() == "<p></p>") {
				alert("내용은 필수 입력입니다.");
				return;
			}//end if
			
			$("#frm")[0].action="modifyBoardProcess.jsp";
			//jquery에서 자바 객체를 찾으려면 배열로 나온다.
			$("#frm").submit();
			
		});//click
		
		$("#btnDelete").click(function() {

			if(confirm("글을 정말 삭제하시겠습니까?")) {
				$("#frm")[0].action="removeBoardProcess.jsp";
				$("#frm").submit();
			}
			
		});//click
	});//ready
</script>

</head>
<body>
	<header data-bs-theme="dark">
		<jsp:include page="../fragments/header.jsp"></jsp:include>
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
				<div class="col-md-7">
					<h2>아무말 대잔치 글읽기</h2>
					<form method="post" name="frm"
						id="frm">
						<table>
							<tr>
								<td>제목</td>
								<td><input type="text" name="title" id="title"
									style="width: 400px" value="${bDTO.title}"/></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea name="content" id="content"><c:out value="${bDTO.content}"/></textarea></td>
							</tr>
							<tr>
								<td>ID/IP</td>
								<td><c:out value="${bDTO.id} / ${bDTO.ip}"/></td>
							</tr>
							<tr>
								<td>조회수</td>
								<td><c:out value="${bDTO.cnt}"/>건</td>
							</tr>
							<tr>
								<td>작성일</td>
								<td><fmt:formatDate value="${bDTO.input_date}" pattern="yyyy-MM-dd EEEE a HH:mm"/></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center;">
									<c:if test="${ sessionScope.userId eq bDTO.id}">
									<input type="hidden" name="num" value="${param.num}"/>
									<button onclick="return false" class="btn btn-success" id="btnModify">글수정</button> 
									<button onclick="return false" class="btn btn-success" id="btnDelete">글삭제</button> 
									</c:if>
									<a href="boardList.jsp" class="btn btn info">리스트</a>
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>
			<hr class="featurette-divider">
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- /.container -->
		<!-- FOOTER -->
		<footer class="container">
			<p class="float-end">
				<jsp:include page="../fragments/footer.jsp"></jsp:include>
			</p>
		</footer>
	</main>

</body>
</html>