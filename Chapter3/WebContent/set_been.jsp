<%@ page import = "chapter3.Bean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Beanを使ったsetのサンプル</title>
</head>
<body>
	<h3>Beanを使ったSetのサンプル</h3>

	<%
	Bean bean = new Bean();
	request.setAttribute("bean", bean);
	%>

	<c:set target = "${bean }" property = "name" value = "ABC" />
	<c:set target = "${bean }" property = "id" value = "${123 }" />

	ID:<c:out value="${bean.id }" /> <br />
	NAME:<c:out value="${bean.name }" /> <br />

</body>
</html>