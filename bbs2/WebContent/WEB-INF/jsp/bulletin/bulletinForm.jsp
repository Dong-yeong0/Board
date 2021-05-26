<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bulletinForm.jsp</title>
<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function() {
		CKEDITOR.replace('content', {
			filebrowserUploadUrl: '${pageContext.request.contextPath }/fileUpload',
			height: '600px',
			width: '800px'
		});
	})
</script>
</head>
<body>
	<div>
		<h1>게시글 등록</h1>
	</div>
	<div>
		<form id="frm" action="memberJoin.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="150">제목</th>
						<td width="300"><input type="text" id="memberId"
							name="memberId"></td>
					</tr>
					<tr>
						<th width="150">내용</th>
						<td width="300">
							<textarea id="content" name="content"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">올리기</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='main.do'">메인으로</button>
			</div>
		</form>
	</div>
</body>
</html>