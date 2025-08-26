<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="background-color: blue; color: white;">英和辞書</h1>



<form onsubmit="return validateForm()" action="Search" method="get" style="display: flex; align-items: center; gap: 10px;">

<div style="display: flex; align-items: center; gap: 10px;">
	<div>英単語:</div>
	<input type="text" name="text" id="textInput">
	<input type="submit" value="検索">
</div>
<div style="display: flex; align-items: center; gap: 10px;"> 
  	<div>最大検索数:</div>
	<input type="number" id="quantity" name="quantity" min="1" max="50" step="1" value="1">
</div>
</form>

<hr>


<c:forEach var="row" items="${results}">
  <h1 style="background-color: blue; color: white;">${row[0]}</h1>
  <div>${fn:replace(row[1], " / ", "<br>")}</div>
</c:forEach>
<c:if test="${hitCount == 0}">
  <p>検索結果は0件でした。</p>
</c:if>

</body>
</html>


<script>
  function validateForm() {
    const input = document.getElementById("textInput").value.trim();
    const alphabetRegex = /^[A-Za-z]+$/;
    
    if (input === "" || !alphabetRegex.test(input)) {
      alert("アルファベットを入力してください。");
      return false; // フォーム送信をキャンセル
    }   
   
    return true; // フォーム送信を許可
  }
</script>