<%@ page import = "chapter3.Bean" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forEachでListを処理するサンプル</title>
</head>
<body>
	<h3>forEachでListを処理するサンプル</h3>
	<%
		List<Bean> beanList = getBeanList();
		request.setAttribute("beanList", beanList);
	%>

	<c:forEach items = "${beanList }" var = "bean" varStatus = "status">
		<c:out value = "${status.count }" /> 人目：
		IDは <c:out value = "${bean.id }" />。
		名前は <c:out value = "${bean.name }" />
		<br>
	</c:forEach>


</body>
</html>

<%!
	private static String[] names = {"太郎", "花子"};

	private static List<Bean> getBeanList(){
		List<Bean> ret = new ArrayList<Bean>();
		for(int i = 0; i < names.length; i++){
			Bean bean = new Bean();
			bean.setId(i);
			bean.setName(names[i]);
			ret.add(bean);
		}
		return ret;
	}
%>