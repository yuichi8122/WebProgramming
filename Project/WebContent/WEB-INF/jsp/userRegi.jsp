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
	<title>ユーザ登録</title>
	</head>
		<body class="center container">
		<div class="right header">
			<label>${userInfo.name} さん</label>
			<label><a href="LoginServlet">ログアウト</a></label>
		</div>

		<div>
	  <h1>ユーザ新規登録</h1>
	  </div>

	<form method ="post" action="UserRegi">
	   <div class="margin">
	    <label for="name" class="albino">ログインID</label>
	    <input type="text" id="name" name="loginId">
	  </div>
	   <div class="margin" >
	    <label for="name" class="albino">パスワード</label>
	    <input type="password" id="name" name="password">
	  </div>
	   <div class="margin" >
	    <label for="name" class="albino">パスワード（確認）</label>
	    <input type="password" id="name" name="confirmPassword">
	  </div>
	   <div class="margin" >
	    <label for="name" class="albino">ユーザ名</label>
	    <input type="text" id="name" name="name">
	  </div>
	  <div class="margin">
	    <label for="mail"  class="albino">生年月日
	    </label>
	    <input type="date" id="name" name="birthdate">
	  </div>

	  <c:if test="${errMsg != null}">
			<div class="alert alert-danger example1" role="alert">${errMsg}</div>
		</c:if>




	  <button type="submit">登録</button>

	  </form>

		<p class="left"><a href="UserListServlet">戻る</a></p>
	</body>
</html>

<!-- mirgin,color,container,albino,text_align使用 -->