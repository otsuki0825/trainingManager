<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
      crossorigin="anonymous">
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<div class="position-relative">
  <div class="p-2 mb-2 bg-primary text-white">筋トレ管理</div>
</div>
<div class="mx-auto p-2" style="width: 300px;">
<%
 String newUser = (String)request.getAttribute("newUser");
 String ng = (String)request.getAttribute("ng");
if(newUser != null){
%>
 <div class="alert alert-danger" role="alert">
  <%=newUser %>
 </div>
<%} %>
<%
 if(ng != null){
%>
  <div class="alert alert-danger" role="alert">
  <%=ng %>
  </div>
<%} %>
<form action="/trainingManager/LoginServlet" method="post" >
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">ID</label>
	  <input type="text" class="form-control" name="id" id="exampleFormControlInput1">
	</div>
	 <label for="inputPassword5" class="form-label">パスワード</label>
	 <input type="password" name="password" id="inputPassword5" class="form-control" aria-labelledby="passwordHelpBlock">
	<div id="passwordHelpBlock" class="form-text"></div>
	<br>
	<input type="submit" class="btn btn-outline-primary" value="ログイン">
	<br>
	<br>
	<p><a href="/trainingManager/jsp/newUserRegist.jsp" class="link-primary">新規登録</a></p>
</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous">
</script>
</body>
</html>