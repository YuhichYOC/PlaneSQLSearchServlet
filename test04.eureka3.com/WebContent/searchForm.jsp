<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://test04.eureka3.com/tags/Result"
           prefix="result"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<%@ include file="commonreference.jsp" %>

<title>
Insert title here
</title>
</head>

<body>

<div class="centerContents">

<%@ include file="commonheader.jsp" %>

<form action="searchForm.jsp" method="post">
<div class="oneLineInSearchForm">
郵便番号上3桁・範囲検索<br/>
</div>
<div class="oneLineInSearchForm">
<span class="oneItemInSearchForm1of5">開始条件</span>
<span class="oneItemInSearchForm2of5"></span>
<span class="oneItemInSearchForm3of5">終了条件</span>
<span class="oneItemInSearchForm4of5"></span>
<span class="oneItemInSearchForm5of5"></span>
</div>
<div class="oneLineInSearchForm">
<span class="oneItemInSearchForm1of5"><input type="text" name="codeFrom" class="oneItemInSearchForm1of5"></span>
<span class="oneItemInSearchForm2of5">〜</span>
<span class="oneItemInSearchForm3of5"><input type="text" name="codeTo" class="oneItemInSearchForm3of5"></span>
<span class="oneItemInSearchForm4of5"></span>
<span class="oneItemInSearchForm5of5"></span>
</div>
<div class="oneLineInSearchForm">
<span class="oneItemInSearchForm1of5"></span>
<span class="oneItemInSearchForm2of5"></span>
<span class="oneItemInSearchForm3of5"></span>
<span class="oneItemInSearchForm4of5"></span>
<span class="oneItemInSearchForm5of5"><input type="submit" value="submit"></span>
</div>
</form>
</div>

<div class="centerContents">
<% String codeFrom = request.getParameter("codeFrom"); %>
<% String codeTo = request.getParameter("codeTo"); %>
<result:Result conditionCodeFrom="<%=codeFrom %>" conditionCodeTo="<%=codeTo %>"/>
</div>

</body>

</html>