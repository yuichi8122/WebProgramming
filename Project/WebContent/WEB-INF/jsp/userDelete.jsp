<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="text_align.css">
<link rel="stylesheet" href="color.css">
<link rel="stylesheet" href="margin.css">
<link rel="stylesheet" href="container.css">
<link rel="stylesheet" href="albino.css">
<link rel="stylesheet" href="height.css">
<title>ユーザ削除確認</title>
</head>
<body class="container center">
	<div class="right header">
		<label>${userInfo.name} さん</label>
			<label><a href="LoginServlet">ログアウト</a></label>
	</div>
	<div>
		<h1>ユーザ削除確認</h1>
	</div>

	<div class="albino_deco margin">ログインID:${user.loginId}</div>
	<div class="albino_deco margin">を本当に削除してもよろしいでしょうか。</div>

	<div>
	<form action="UserDelete" method="post">
		<label class="margin"><button type="submit" class="height">
				<a href="UserListServlet">キャンセル</a>
			</button></label> <label class="margin"><button type="submit" class="height">OK</button></label>
			<input type="hidden" name="id" value="${user.id}">
	</form>
	</div>
</body>
</html>

<!--height追加 -->