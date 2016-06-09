<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Promena lozinke</title>

<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">	
<script src="<c:url value="/js/bootstrap.js"/>"></script>
 <script src="<c:url value="/js/jquery.min.js"/>"></script>

</head>



<body>
<strong>Azuriranje lozinke</strong>Vreme vazenja vase lozinke je isteklo, azurirajte vasu lozinku!
<br>
<br>
<br>
<c:url var="action" value="/logIn" />
    <form:form id="formChangePass" action="${action}" method="post" modelAttribute="user">
    <table>
    <fildSet>
      <tr>
     <td>
      <form:label path="korisnickoIme">
       <b> Korisnicko ime: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control"  path="korisnickoIme" value="${user.korisnickoIme}" cssErrorClass="error" readonly="true" />
      <form:errors path="korisnickoIme" cssClass="errorMessage" />
     </td>
    </tr>
    
    
    
      <tr>
     <td>
      <form:label path="lozinka">
       <b> Trenutna lozinka: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control" type ="password" path="lozinka" cssErrorClass="error" />
      <form:errors path="lozinka" cssClass="errorMessage" />
     <td>
    </tr>
    
    <tr>
     <td>
      <form:label path="novaLozinka">
       <b> Nova lozinka: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control" type ="password"  path="novaLozinka" cssErrorClass="error" />
      <form:errors path="novaLozinka" cssClass="errorMessage" />
     </td>
    </tr>

    </fildSet>
      
      <tr>
      	<td>
      		<button type="submit" name="changePass" class="btn btn-success">
       		<b> Promeni lozinku </b>
      		</button>
       </td>
      </tr>
     </table>
   </form:form>


<c:if test="${pograsnaStara != null}">
		<strong>Greska</strong> Unesite ispravnu trenutnu lozinku
</c:if>

<c:if test="${praznaNova != null}">
		<strong>Greska</strong> Nova loznika ne sme biti prazna
</c:if>


</body>
</html>