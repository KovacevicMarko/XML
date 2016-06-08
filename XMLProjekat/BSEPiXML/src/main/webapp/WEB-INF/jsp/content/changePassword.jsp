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


<script type="text/javascript">

</script>


</head>



<body>
<c:url var="action" value="/logIn" />
    <form:form id="formChangePass" action="${action}" method="post" modelAttribute="user">
    <table>
    <fildSet>
      <tr>
     <td>
      <form:label path="korisnickoIme">
       <b> Stara lozinka: </b>
      </form:label>
     </td>
     <td>
      <form:input class="form-control" type ="password"  path="korisnickoIme" cssErrorClass="error" />
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


</body>
</html>