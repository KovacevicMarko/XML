<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>

<html>
	<head>
		<title>Prijava</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="<c:url value="/css/log.css" />" rel="stylesheet">	
		
	<script src="<c:url value="/js/jquery.min.js"/>"></script>	
	<script src="<c:url value="/js/logJS.js"/>"></script>
	
<script type="text/javascript">


window.onload = function () {
	document.getElementById("lozinka").onchange = validatePassword;
	document.getElementById("lozinka1").onchange = validatePassword;
}
function validatePassword(){
var pass2=document.getElementById("lozinka1").value;
var pass1=document.getElementById("lozinka").value;
if(pass1!=pass2)
	document.getElementById("lozinka1").setCustomValidity("Lozinka se ne poklapa! Unesite ispravno lozinku");
else
	document.getElementById("lozinka1").setCustomValidity('');	 
//empty string means no validation error
}
</script>
		
	</head>
	
	<c:if test="${sessionScope.admin!=null}">
		<c:redirect url="./read.jsp" />
	</c:if>
	<body>
	
	
	
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#"  id="register-form-link">Register</a>
							</div>
							
						</div>
						<hr>
					</div>
					<div align="center" class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="LoginController" method="post" role="form" style="display: block;">
									<div class="form-group">
									<c:if test="${porukaPostoji != null}">
									<c:if test="${not empty porukaPostoji}">
							
 									<strong>Neuspesno registrovanje!</strong>Korisnik sa tim korisnickim imenom vec postiji
							
									</c:if>	
									</c:if>
									</div>
									<div class="form-group">
										<input type="text" required name="korisnickoIme" id="korisnickoIme" tabindex="1" class="form-control" placeholder="Korisnicko ime" value="">
									</div>
									<div class="form-group">
										<input type="password" required name="logLozinka" id="logLozinka" tabindex="2" class="form-control" placeholder="Lozinka">
									</div>
									<div class="form-group text-center">
										
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Prijavi se">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
									
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="RegisterController" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" required name="ime" id="ime" tabindex="1" class="form-control" placeholder="Ime" value="">
									</div>
									<div class="form-group">
										<input type="text" required name="prezime" id="prezime" tabindex="1" class="form-control" placeholder="Prezime" value="">
									</div>
									<div class="form-group">
										<input type="email" required name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
									</div>
									<div class="form-group">
										<input type="text" required name="korisnickoIme" id="korisnickoIme" tabindex="1" class="form-control" placeholder="Korisnicko ime" value="">
									</div>
									<div class="form-group">
										<input type="password" required name="lozinka" id="lozinka" tabindex="2" class="form-control" placeholder="Lozinka">
									</div>
									<div class="form-group">
										<input type="password" required name="lozinka1" id="lozinka1" tabindex="2" class="form-control" placeholder="Ponovite lozinku">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Registruj se">
											</div>
										</div>
									</div>
								</form>
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<body>	
</html>