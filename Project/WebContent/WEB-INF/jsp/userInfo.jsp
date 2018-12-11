<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="text_align.css">
<link rel="stylesheet" href="color.css">
<link rel="stylesheet" href="margin.css">
<link rel="stylesheet" href="container.css">
<link rel="stylesheet" href="albino.css">
<title>ユーザ情報詳細参照</title>
</head>

<body class="center container">

	<div class="right header">
			<label>${userInfo.name} さん</label>
			<label><a href="LoginServlet">ログアウト</a></label>
		</div>

	<h1>ユーザ情報詳細参照</h1>
	<div class="margin">
		<label class="albino200">ログインID</label> <label class="albino200">${user.loginId}</label>
	</div>

	<div class="margin">
		<label class="albino200">ユーザ名</label> <label class="albino200">${user.name} </label>
	</div>

	<div class="margin">
		<label class="albino200">生年月日</label> <label class="albino200">${user.birthDate}</label>
	</div>


	<div class="margin">
		<label class="albino200">登録日時：</label> <label class="albino200"><time>${user.createDate}</time></label>
	</div>

	<div class="margin">
		<label class="albino200">更新日時：</label> <label class="albino200"><time>${user.updateDate}</time></label>
	</div>
	<div>
		<p class="left">
			<a href="UserListServlet">戻る</a>
		</p>
	</div>
</body>
</html>