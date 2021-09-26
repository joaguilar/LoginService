<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
form{
	background-color: skyblue;
	width: 200px;
	height: 180px;
	border-style: solid;
	border-color: blue;
	padding: 10px;
}
</style type="text/javascript">
<script>
	function gotoRegister(){
		window.location.href="/register";
	}


</script>

<h1>Hello I am the login page</h1>
<br/>
	<div style="color: red;">
	<h3>
	${errorMessage}
	</h3>
	</div>

<br/>
<div>
<form action="/login" method="post">
	<div>
		Username: <input type="text" name="userName"/>
	</div>
	<div><br/></div>
	<div>
		Password:&nbsp; <input type="password" name="password"/>
	</div>
	<div><br/></div>
	<div>
		<input type="submit" value="Login"/>
	</div>
	<div><br/></div>
	<div>
		<input type="button" name="Register" value="Register" onclick="gotoRegister()" />
	</div>
</form>
</div>