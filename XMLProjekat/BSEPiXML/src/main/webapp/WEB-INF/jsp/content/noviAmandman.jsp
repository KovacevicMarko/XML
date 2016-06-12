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

<c:url var="action" value="/amandman" />
<form:form id="formNewAkt" action="${action}" method="post" modelAttribute="amandman">
			<table class="dodavanjeAkta">
			<fildSet>						
				<tr>
					<td><form:label path="ciljIzmene">Unesite novi amandman:</form:label></td>
					<td>
						<form:textarea path="ciljIzmene" rows="5" cols="50" />
						<form:errors path="ciljIzmene" cssClass="error" />
					</td>
					
											
				</tr>
				
				</fildSet>
				<tr>
					<td>
						<button type="submit" class="btn btn-success" name="save" >
							<b> DodajAmandman </b>
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