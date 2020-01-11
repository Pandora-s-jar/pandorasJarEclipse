<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/assistenza.css">
		<link rel="stylesheet" href="css/bootstrap-4.4.1-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/assistenza.css">
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  		<script src="css/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
  		<script src="scripts/helpScript.js"></script>
	</head>

	<body>
		<div class="generalDiv">
		<div class="imageLeft">
			<h4 class="welcomeTop text-monospace font-italic font-weight-bold">Benvenuto nella pagina dell'assistenza!</h4>
			<h4 class="text-monospace font-italic font-weight-bold">Questa email sara' indirizzata a ${emailTo}</h4>
			<img src="Assets/mailLogo.png" alt="IMG">
		</div>
		<div class="formCenter">
			<form method="POST" class="form-group">
  					<div class="input-group mb-3">
	  					<div class="input-group-prepend">
	   						<span class=" input-group-text icone fa fa-address-book"></span>
	   					</div>
						<c:if test="${not logged}">
							<input type="text" class="form-control-lg" id="nome" placeholder="Nome">
						</c:if>
						<c:if test="${logged}">
 							<input type="text" class="form-control-lg" id="nome" value="${name}" readonly>
						</c:if>
					</div>
 					<div class="input-group mb-3">
	  					<div class="input-group-prepend">
	   						<span class="input-group-text icone fa fa-envelope"></span>
	   					</div>
						<c:if test="${not logged}">
							<input type="text" class="form-control-lg" id="email" placeholder="Email">
						</c:if>
						<c:if test="${logged}">
 							<input type="text" class="form-control-lg" id="email" value="${email}" readonly>
						</c:if>
					</div>
 					<div class="input-group mb-3">
	  					<div class="input-group-prepend">
	   						<span class="input-group-text icone fa fa-info-circle"></span>
	   					</div>
 						<input type="text" class="form-control-lg fieldWidth" id="oggetto" placeholder="Oggetto" required>
  					</div>
 				<!--<div class="form-group">-->
 					<div class="input-group mb-3">
	  					<div class="input-group-prepend">
	   						<span class=" input-group-text iconaMessaggio fa fa-comment"></span>
	   					</div>
 						<textarea wrap="soft" class="form-control areaT" rows="8" id="message" placeholder="Messaggio" required></textarea>
  					</div>

 					<!-- buttons -->
 				<div class="form-group">
   					<button type="reset" class="btn btn-secondary">
     					<span class="fa fa-remove"></span> Reset
   					</button>
   					<button type="submit" class="btn btn-primary">
     					<span class="fa fa-envelope"></span> Submit
   					</button>
 				</div>
			</form>
		</div>
		</div>
	</body>
</html>