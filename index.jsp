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

<div style="display: flex; align-items: center; gap: 10px;">



<form action="Search" method="get">

<div style="display: flex; align-items: center; gap: 10px;">
	<div>英単語:</div>
	<input type="text" name="text">
	<input type="submit" value="検索">
</div>
<div style="display: flex; align-items: center; gap: 10px;"> 
  	<div>最大検索数数:</div>
	<input type="number" id="quantity" name="quantity" min="1" max="50" step="1" value="1">
</div>
</form>

</div>
<h2>検索結果</h2>

<ul>
  <c:forEach var="row" items="${results}">
    <li><strong>${row[0]}</strong>: ${row[1]}</li>
  </c:forEach>
</ul>

</body>
</html>