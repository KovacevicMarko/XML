<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">	

</head>
<body>


<nav class="navbar navbar-default" role="navigation">
<div class="container-fluid">
	
	<div class="navbar-header">
	
		<a class="navbar-brand" href="homePage">Pocetna strana</a>
		<a class="navbar-brand" href="homePage">Zakazane sednice</a>
		<a class="navbar-brand" href="homePage">Poslanici</a>
		<c:if test="${not empty korisnik}">
			<a class="navbar-brand" href="PrikazPrijatelja.jsp">Moji prijatelji</a>
			<a class="navbar-brand" href="MojProfil.jsp">Moj profil</a>
			<a class="navbar-brand" href="MojePosete.jsp">Moje posete</a>
		</c:if>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		
		<ul class="nav navbar-nav navbar-right">
		
			<c:if test="${empty korisnik}">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Login
					<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="logIn">Logovanje odbornika</a></li>
					<li class="divider"></li>
					
			</c:if>
			
			<c:if test="${not empty korisnik}">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
				
							<span><b>Dobrodošli, <c:out value="${korisnik.imeKorisnika}" /> <c:out value="${korisnik.prezimeKorisnika}" /></b>  </span>
								<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="IzmeniKorisnik.jsp">Izmeni profil</a></li>
									<li><a href="PrikazPrijatelja.jsp">Moji prjatelji</a></li>
									<li><a href="read.jsp">Restorani</a></li>
									<li class="divider"></li>

									<li><a href="./LogoutController"> Odjavite se</a></li>
							
							</ul>
						</li> 
			</c:if>
			
			<c:if test="${not empty menadzer}">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
				
							<span><b>Dobrodošli, <c:out value="${menadzer.imeMenadzera}" /> <c:out value="${menadzer.prezimeMenadzera}" /></b>  </span>
								<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="IzmeniMenadzer.jsp">Moj profil</a></li>
									<li class="divider"></li>

									<li><a href="./LogoutController"> Odjavite se</a></li>
							
							</ul>
						</li> 
			</c:if>
			
			<c:if test="${not empty admin}">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
				
							<span><b>Dobrodošli, <c:out value="${admin.imeMenadzera}" /> <c:out value="${admin.prezimeMenadzera}" /></b>  </span>
								<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="IzmeniMenadzer.jsp">Moj profil</a></li>
									<li class="divider"></li>

									<li><a href="./LogoutController"> Odjavite se</a></li>
							
							</ul>
						</li> 
			</c:if>
			
		</ul>
	</div>
	
</div>

	
<script src="<c:url value="/js/jquery.min.js"/>"></script>	
<script src="<c:url value="/js/bootstrap.js"/>"></script>


</body>
</html>