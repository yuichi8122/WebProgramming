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
<link rel="stylesheet" href="albino.css">
<link rel="stylesheet" href="container.css">
<link rel="stylesheet" href="height.css">
<title>ユーザ一覧</title>
</head>
<body class="center container">
	<div class="right header">
		<label>${userInfo.name} さん</label> <label><a
			href="LoginServlet">ログアウト</a></label>
	</div>

	<h1>ユーザ一覧</h1>
	<p class="right">
		<a href="UserRegi">新規登録</a>
	</p>
	<form method="post" action="UserListServlet">
	<div class="margin">



			<label for="code" class="albino">ログインID</label> <input type="text"
				id="name" name="loginIdFind">
	</div>


	<div class="margin">
		<label for="name" class="albino">ユーザ名</label> <input type="text"
			id="name" name="nameFind">
	</div>

	<div class="margin">
		<label for="continent" class="albino">生年月日</label> <input type="date"
			name="birthDateAfter" max="9999-12-31"> <label>～</label> <label></label>
		<input type="date" name="birthDateBefore" max="9999-12-31">
	</div>
	<button type="submit" value="検索" class="height">検索</button>

	</form>

	<hr>

	<table border="1" class="width">
		<tr class="lightgray">
		<thead>
			<tr>
				<th>ログインID</th>
				<th>ユーザ名</th>
				<th>生年月日</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.loginId}</td>
					<td>${user.name}</td>
					<td>${user.birthDate}</td>
					<!-- TODO 未実装；ログインボタンの表示制御を行う -->



					<td>	<a class="blue" href="UserDetailServlet?id=${user.id}">詳細</a>


						<c:if test = "${userInfo.loginId=='admin'||userInfo.loginId==user.loginId}">
							<a class="red" href="UserUpdate?id=${user.id}">更新</a>

						</c:if>
						<c:if test="${userInfo.loginId=='admin'}">
							<a class="green" href="UserDelete?id=${user.id}">削除</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</body>
</html>