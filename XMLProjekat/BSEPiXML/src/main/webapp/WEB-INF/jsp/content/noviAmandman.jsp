<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novi amandman</title>
<script src="/js/jquery.min.js"></script>	
<script src="/js/bootstrap.js"/></script>

</head>
<body>
<br><br><br><br><br>

<c:url var="action" value="/noviAmandman" />
<form:form id="formNewAkt" action="${action}" method="post" modelAttribute="amandman">
			<table class="dodavanjeAmandmana">
			<fildSet>
				
				<tr>
					<td><form:label path="akt">Pravni osnov:</form:label></td>
					<td>
					<form:select path="akt">
   						<form:option value="NONE" label="--- Select ---"/>
					</form:select>
					</td>
				</tr>
				<tr>
					 <td><form:label path="pravniOsnov">Pravni osnov:</form:label></td>
					 <td>
					 <form:input  path="pravniOsnov" cssErrorClass="error" />
      				 <form:errors path="pravniOsnov" cssClass="errorMessage" />
					 </td>	
				</tr>
				
				<tr>
					 <td><form:label path="predmetIzmene">Predmet izmene:</form:label></td>
					 <td>
					 <form:input  path="predmetIzmene" cssErrorClass="error" />
      				 <form:errors path="predmetIzmene" cssClass="errorMessage" />
					 </td>	
				</tr>
				
				<tr>
					 <td><form:label path="ciljIzmene">Cilj izmene:</form:label></td>
					 <td>
					 <form:input  path="ciljIzmene" cssErrorClass="error" />
      				 <form:errors path="ciljIzmene" cssClass="errorMessage" />
					 </td>	
				</tr>
				
				</fildSet>
				<tr>
					<td>
						<button type="submit" class="btn btn-success" name="save" >
							<b> Predlozi </b>
						</button>
						&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-danger" name="cancel" >
							<b> Odustani </b>
						</button>
					</td>				
				</tr>
				
			</table>							
</form:form>

</body>
</html>