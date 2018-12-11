<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="text_align.css">
<link rel="stylesheet" href="margin.css">
<link rel="stylesheet" href="color.css">
<link rel="stylesheet" href="container.css">
<title>ログイン画面</title>
</head>
<body class="center container">
	<h1>ログイン画面</h1>

	<form method="post" action="LoginServlet">
		<div class="margin">
			<label for="name">ログインID</label> <input type="text" id="name" name="loginId">

		</div>
		<div class="margin">
			<label for="name">パスワード</label> <input type="password" name="password"><br>
		</div>

		<c:if test="${errMsg != null}">
			<div class="alert alert-danger example1" role="alert">${errMsg}</div>
		</c:if>


		<p class="hello">
			<button type="submit">ログイン</button>
		</p>
	</form>

</body>
</html>

<!-- center.cssとmaagin.cssを使用 -->