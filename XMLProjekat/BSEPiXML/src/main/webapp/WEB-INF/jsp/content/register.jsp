<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrovanje korisnika</title>

<script src="/js/jquery.min.js"></script>	
<script src="/js/bootstrap.js"/></script>
</head>
<body>
	
			<c:if test="${postojiVec != null}">
				<c:if test="${not empty postojiVec}">
					<strong>Neuspesno registrovanje!</strong>Korisnik sa tim korisnickim imenom vec postiji							
				</c:if>	
			</c:if>
	

<c:url var="action" value="/logIn" />
    <form:form id="formRegister" action="${action}" method="post" modelAttribute="user">
    <table>
    <fildSet>
      <tr>
     <td>
      <form:label path="korisnickoIme">
       <b> Korisnicko ime: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control"  path="korisnickoIme" cssErrorClass="error" />
      <form:errors path="korisnickoIme" cssClass="errorMessage" />
     </td>
    </tr>
    
      <tr>
     <td>
      <form:label path="lozinka">
       <b> Lozinka: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control" type ="password" path="lozinka" cssErrorClass="error" />
      <form:errors path="lozinka" cssClass="errorMessage" />
     <td>
    </tr>
    
    <tr>
     <td>
      <form:label path="ime">
       <b> Ime: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control"  path="ime" cssErrorClass="error" />
      <form:errors path="ime" cssClass="errorMessage" />
     </td>
    </tr>
    
       
      <tr>
     <td>
      <form:label path="prezime">
       <b> Prezime: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control"  path="prezime" cssErrorClass="error" />
      <form:errors path="prezime" cssClass="errorMessage" />
     </td>
    </tr>
      
      <tr>
     <td>
      <form:label path="email">
       <b> Email: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control" path="email" cssErrorClass="error" />
      <form:errors path="email" cssClass="errorMessage" />
     </td>
    </tr>
      
      
      <tr>
			<td><form:label path="uloga">Uloga:</form:label></td>
			<td>
			<form:select class="form-control" path="uloga">
   				<form:option value="Odbornik" label="Odbornik"/>
   				<form:option value="Predsednik" label="Predsednik"/>
			</form:select>
			</td>
	  </tr>

    </fildSet>
      
      <tr>
      	<td>
      		<button type="submit" name="register" class="btn btn-success">
       		<b> Registruj se </b>
      		</button>
       </td>
      </tr>
     </table>
   </form:form>

</body>
</html>