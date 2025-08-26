<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="background-color: blue; color: white;">英和辞書</h1>


<div>英単語:</div>
<form action="Search" method="get">
	<input type="text" name="text">
	<input type="submit" value="検索">
</form>
<div>
  	<div>最大検索数数:</div>
	<input type="number" id="quantity" name="quantity" min="1" max="10" step="1">
</div>


</body>
</html>