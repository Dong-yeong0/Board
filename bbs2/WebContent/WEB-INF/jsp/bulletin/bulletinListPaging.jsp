<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bulletinListPaging.jsp</title>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script>
	function goPage(page){
		location.href = "bulletinListPaging.do?page=" + page;
	}
	function formSubmit(id){
      frm.id.value = id;
      frm.submit();
	}
</script>

</head>
<body>

	<form id="frm" action="bulletinSelete.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center">
		<h3>게시물(Paging) 리스트</h3>
		<div style="width: 80%">
			<table class="table" border="1">
				<tr>
					<th width="100">순번</th>
					<th width="200">제목</th>
					<th width="150">작성일자</th>
					<th width="100">조회수</th>
				</tr>
				<c:forEach items="${bulletinList }" var="vo">
					<tr onclick="formSubmit(${vo.id })">
						<td>${vo.id }</td>
						<td>${vo.title }</td>
						<td>${vo.regDate }</td>
						<td>${vo.hit } <i class="glyphicon glyphicon-thumbs-up"></i> <i class="glyphicon glyphicon-thumbs-up"></i></td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button type="button" onclick="location.href='main.do'">첫
					페이지</button>
				<c:if test="${!empty id}">
					<button type="button" onclick="location.href='bulletinForm.do'">등록</button>
				</c:if>
			</div>

			<!-- 페이징 호출 -->
			<jsp:include page="../common/paging.jsp" flush="true">
				<jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
				<jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
				<jsp:param name="startPageNo" value="${paging.startPageNo}" />
				<jsp:param name="pageNo" value="${paging.pageNo}" />
				<jsp:param name="endPageNo" value="${paging.endPageNo}" />
				<jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
				<jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
			</jsp:include>
			<!-- 페이징 호출 종료 -->
		</div>
	</div>
</body>
</html>