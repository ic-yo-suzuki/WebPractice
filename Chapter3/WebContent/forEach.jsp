<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forEachのサンプル</title>
</head>
<body>
	<h3>forEachのサンプル</h3>

	<c:forEach begin = "1" end = "9" var = "i">
		<c:out value = "${i}" /> は
		<c:choose>
			<c:when test = "${i % 2 == 0}">
				偶数
			</c:when>
			<c:otherwise>
				奇数
			</c:otherwise>
		</c:choose>
		です <br>
	</c:forEach>

</body>
</html>