<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.css">
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
	    <link rel="stylesheet" href="css/profileStyle.css">
		<link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	 	<script src="scripts/profileScript.js"></script>
	 	
	 </head>
	<body>
		<c:if test="${not logged}">
			<div class="modal" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Error</h4>
							<button type="button" class="close modalCloseBtn" data-dismiss="modal">&times;</button>
						</div>

						<div class="modal-body">
							You're not logged
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-danger modalCloseBtn" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${logged}">
			<jsp:include page="header.jsp" />
			<div id="row">
				<div id="first_column">
					<jsp:include page="profileMenu.html"></jsp:include>
				</div>

				<form id="profileDetails" method="post" action="changeProfileDetails">
					<div id="second_column">
						<div class="text-center words" id="profilePhoto">
							<c:if test="${empty user.image}">
								<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="avatar img-circle img-thumbnail" alt="avatar">
							</c:if>
							<c:if test="${not empty user.image}">
								<img src="${user.image}">
							</c:if>
							<c:if test="${not friend}">
								<h6>Upload a different photo...</h6>
								<input type="file" id="inputFile" class="text-center center-block file-upload btn btn-primary">
							</c:if>
						</div>
						<div id="details">
							<table class="table table-hover jumbotron" id="tableWords" >
								<tbody id="tbody-details">
									<tr>
										<td>Username:</td>
										<td><input type="text" name="inputUsername" id="inputUsername" readonly value="${user.username}"/></td>
										<c:if test="${not friend}">
											<td><button type="button" class="btn btn-primary btn-sm fas fa-edit background-color" id="btnChangeUsername"></button></td>
										</c:if>
									</tr>
									<tr>
										<td>Email:</td>
										<td><input type="text" name="inputEmail" id="inputEmail" readonly value="${user.email}"/></td>
										<c:if test="${not friend}">
											<td><button type="button" class="btn btn-primary btn-sm fas fa-edit background-color" id="btnChangeEmail"></button></td>
										</c:if>
									</tr>
									<tr>
										<td>Password:</td>
										<td><input type="text" name="inputPassword" id="inputPassword" readonly value="${user.password}"/></td>
										<c:if test="${not friend}">
											<td><button type="button" class="btn btn-primary btn-sm fas fa-edit background-color" id="btnChangePassword"></button></td>
										</c:if>
									</tr>
									<tr>
										<td>Description:</td>
										<td><input type="text" name="inputDescription" id="inputDescription" readonly value="${user.description}"/></td>
										<c:if test="${not friend}">
											<td><button type="button" class="btn btn-primary btn-sm fas fa-edit background-color" id="btnChangeDescription"></button></td>
										</c:if>
									</tr>
								</tbody>
							</table>
						</div>
						<c:if test="${not friend}">
							<input type="submit" value="Save" id="saveBtn" class="btn btn-primary background-color"/>
						</c:if>
						<div id="friendsList" class="jumbotron jumbotron-fluid" style="text-align:center;">
							<div class="words words-friends">
								<h3 style="margin-top:-50px;" >Friends:</h3>
								<c:forEach items="${user.friends}" var="friend">
									<a href="profile?id=${friend.id}">${friend.username}</a>,
								</c:forEach>
							</div>
							<div id="divAddFriend">
								<c:if test="${not friend}">
									<input type="button" id="addFriend" value="Add friend" class="btn btn-primary btn-center background-color"/>
									<form id="formAddFriend" method="post" action="changeProfileDetails">
										<div id="insideForm">
										</div>
									</form>
								</c:if>
							</div>
						</div>
					</div>
				</form>
			</div>
			<jsp:include page="footer.html" />
		</c:if>
	</body>
</html>

