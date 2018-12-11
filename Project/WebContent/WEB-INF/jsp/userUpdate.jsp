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
		<title>ユーザ更新</title>
	</head>

	<body class="center container">
		<div class="right header">
		<label>${userInfo.name} さん</label>
			<label><a href="LoginServlet">ログアウト</a></label>
		</div>

		<div>
		<h1>ユーザ情報更新</h1>
		</div>

	<form action="UserUpdate" method="post">

	 <div class="margin">
	    <label for="name" class="albino">ログインID</label>
	    <label>${user.loginId}</label>
	  </div>
	   <div class="margin">
	    <label for="name" class="albino">パスワード</label>
	    <input type="password" id="name" name="password">
	  </div>
	   <div class="margin">
	    <label for="name" class="albino">パスワード（確認）</label>
	    <input type="password" id="name" name="confirmPassword">
	  </div>
	   <div class="margin">
	    <label for="name" class="albino">ユーザ名</label>
	    <input type="text" value="${user.name}" id="name" name="name">
	  </div>
	  <div class="margin">
	    <label for="name" class="albino">生年月日
	    </label>
	    <input type="text"value="${user.birthDate}" id="mail" name="birthdate">
	  </div>

	    <c:if test="${errMsg != null}">
			<div class="alert alert-danger example1" role="alert">${errMsg}</div>
		</c:if>

	  <p><button type="submit">更新</button></p>
		<input type="hidden" name="id" value="${user.id}">
	  </form>

		<p class="left"><a href="UserListServlet">戻る</a></p>
		</body>
</html>