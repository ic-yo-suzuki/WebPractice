<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>簡易Twitter</title>
	<link href = "./css/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>


	<c:choose>
		<c:when test="${empty loginUser }">
		<div class = "header">
			<a href = "login">ログイン</a>
			<a href = "signup">登録する</a>
		</div>
		</c:when>
		<c:when test = "${not empty loginUser }">
		<div class = "header">
			<a href = "./">ホーム</a>
			<a href = "settings">設定</a>
			<a href = "logout">ログアウト</a>
		</div>
		<div class = "profile">
			<div class = "name"> <h2> <c:out value = "${loginUser.name }"></c:out></h2></div>
			<div class = "account">
				 @<c:out value = "${loginUser.account }"></c:out>
			</div>
			<div class = "account"><c:out value = "${loginUser.description }"></c:out></div>
		</div>
		</c:when>
	</c:choose>
	<c:if test = "${isShowMessageForm }">
		<div class = "form-area">
			<form action = "newMessage" method = "post">
				いま、どうしてる？<br />
				<textarea name = "message" cols = "100" rows = "5" class = "tweet-box"></textarea>
				<br />
				<input type = "submit" value = "つぶやく">(140文字まで)
			</form>
		</div>
	</c:if>
	<div class = "messages">
		<c:forEach items = "${messages }" var = "message">
		<div class = "message">
			<div class = "account-name">
				<span class = "account"><c:out value = "${message.account }"></c:out></span>
				<span class = "name"><c:out value = "${message.name }"></c:out></span>
			</div>
			<div class = "text"><c:out value = "${message.text }"></c:out></div>
			<div class = "date"><fmt:formatDate value="${message.insertDate }" pattern ="yyyy/MM/dd/ HH:mm:ss" /></div>
		</div>
		</c:forEach>

	</div>


	<div class = "copylight"> Copyright(c) Yoshihiro Suzuki</div>
</body>
</html>