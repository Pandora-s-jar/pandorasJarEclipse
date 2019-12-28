<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
	    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	    <link rel="stylesheet" href="css/profileStyle.css"><link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	 	<script src="js/profileScript.js"></script>
	 	
	 </head>
	<body>
		<div id="row">
			<div id="first_column">
				<nav class="navbar navbar-dark" id="navMenu">
					<ul class="navbar-nav nav-fill w-100">
						<li class="navbar-brand">
							<a class="nav-link" href="#">General</a>
						</li>
						<li class="navbar-brand">
							<a class="nav-link" href="#">Game Statistics</a>
						</li>
						<li class="navbar-brand">
							<a class="nav-link" href="#">Developer Statistics</a>
						</li>						
					</ul>
				</nav>
			</div>
			<div id="second_column">
				<div class="text-center words" id="profilePhoto">
			        <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
			        <h6>Upload a different photo...</h6>
			        <input type="file" class="text-center center-block file-upload btn btn-primary">
		      	</div>
		      	
		      	<div id="profileDetails" class="">
					<table class="table table-hover jumbotron jumbotron-fluid" id="tableWords" >
						<tr>
							<td>Username:</td>
							<td><input type="text" id="inputUsername" disabled="disabled" value="${user.username}"/></td>
							<td><button type="button" class="btn btn-primary btn-sm fas fa-edit" id="btnChangeUsername"></button></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input type="text" id="inputEmail" disabled="disabled" value="${user.email}"/></td>
							<td><button type="button" class="btn btn-primary btn-sm fas fa-edit" id="btnChangeEmail"></button></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="text" id="inputPassword" disabled="disabled" value="${user.password}"/></td>
							<td><button type="button" class="btn btn-primary btn-sm fas fa-edit" id="btnChangePassword"></button></td>
						</tr>
						<tr>
							<td>Description:</td>
							<td><input type="text" id="inputDescription" disabled="disabled" value="${user.description}"/></td>
							<td><button type="button" class="btn btn-primary btn-sm fas fa-edit" id="btnChangeDescription"></button></td>
						</tr>
					</table>
					<input type="submit" method="POST" value="Save" id="saveBtn" class="btn btn-primary"/>
					
				</div>
				<div id="friendsList" class="jumbotron jumbotron-fluid">
					<div class="words words-friends">
						<h3>Friends:</h3>
						<div>
							<c:forEach items="${user.friends}" var="friend">
								<a href="profile/${friend.username}">${friend.username} </a>
							</c:forEach>
						</div>
					</div>
					<input type="button" value="Add friend" class="btn btn-primary"/>
				</div>
			</div>
			
		</div>		
		
	</body>
</html>