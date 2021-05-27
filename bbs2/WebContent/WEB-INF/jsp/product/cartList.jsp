<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<style>
.table {
	vertical-align: middle;
}
#title>th {
	background-color: gray;
	color: white;
	border: 0px solid black;
	border-radius: 10px;
	padding: 20px auto;
}
</style>
</head>
<body>

	<form id="frm" action="" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center" style="margin: 50px auto">
		<h3>장바구니 정보</h3>
		<div style="width: 80%">
			<table class="table" style="text-align: center;">
				<tr id="title">
					<th>회원아이디</th>
					<th>상품명</th>
					<th>이미지</th>
					<th>가격</th>
					<th>수량</th>
					<th>합계</th>
					<th>기능</th>
				</tr>
				<c:forEach items="${cartList }" var="vo">
					<tr>
						<td>${vo.userId }</td>
						<td>${vo.itemName }</td>
						<td><img class="card-img-top"
							style="width: 250px; height: 200px; padding: 20px;"
							src="upload/${vo.itemImage }" alt="..." /></td>
						<c:choose>
							<c:when test="${vo.sale eq 'Y' }">
								<td><span class="text-muted text-decoration-line-through">
										<fmt:formatNumber type="currency" value="${vo.price }"></fmt:formatNumber><br>
								</span>세일가→<fmt:formatNumber type="currency" value="${vo.salePrice }"></fmt:formatNumber>
								</td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="currency" value="${vo.price }"></fmt:formatNumber></td>
							</c:otherwise>
						</c:choose>

						<td>${vo.itemQty }개</td>
						<c:choose>
							<c:when test="${vo.sale eq 'Y' }">
								<td><fmt:formatNumber type="currency"
										value="${vo.itemQty * vo.salePrice }"></fmt:formatNumber></td>
							</c:when>
							<c:otherwise>
								<td><fmt:formatNumber type="currency" value="${vo.price }"></fmt:formatNumber></td>
							</c:otherwise>
						</c:choose>
						<td>
							<form action="deleteCart.do">
								<button class="btn btn-outline-dark" type="submit">비우기</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button class="btn btn-outline-dark" type="button"
					onclick="location.href='productList.do'">다시 둘러보기</button>
			</div>
		</div>
	</div>
</body>
</html>