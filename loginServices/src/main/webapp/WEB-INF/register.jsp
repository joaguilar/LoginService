<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
form{
	background-color: olive;
	width: 200px;
	height: 300px;
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

<h1>Hello I am the Registration page</h1>
<br/>
	<div style="color: red;">
	<h3>
	${errorMessage}
	${registerSuccess}
	</h3>
	</div>

<br/>
<div>
<form action="/set-user" method="post">
	<div>
		Username: <input type="text" name="userName"/>
	</div>
	<div><br/></div>
	<div>
		Email: <input type="text" name="email1"/>
	</div>
	<div><br/></div>
	<div>
		ReType Email: <input type="text" name="email2"/>
	</div>
	<div><br/></div>
	<div>
		Password:&nbsp; <input type="password" name="password1"/>
	</div>
	<div><br/></div>
	<div>
		ReType Password:&nbsp; <input type="password" name="password2"/>
	</div>
	<div><br/></div>

	<div>
		<input type="submit" value="Register"/>
	</div>
</form>
</div>