<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amandmani</title>

		
<script src="/js/jquery.min.js"></script>	
<script src="/js/bootstrap.js"/></script>
</head>
<body>

<div align="center" class="col-xs-3">
	<table  align="center" class="table table-striped table-bordered table-hover table-condensed">
			<caption style="border: inherit; background-color: lightgrey;">Amandmani....</caption>
			<thead class="thead-inverse">
				
					<th>Pravni osnov</th>
					<th>Predlagac</th>
					<th>Datum predlaganja</th>
					<th>Opsirnije</th>	
			</thead>
			<tbody>
				<c:forEach items="${amandmani}" var="amandman">
				<tr>
					<td>${amandman.pravniOsnov}</td>
					<td>${amandman.predlagac.ime} ${amandman.predlagac.prezime}</td>
					<td>${amandman.datumPredlaganja}</td>
					<td>
						<c:url var="action" value="/amandman" />
						<form:form id="formFindDeo" action="${action}" method="post" modelAttribute="amandmanId">
						  <fildSet>	
							<form:input type="hidden" path="id" value="${amandman.ID}" rows="5" cols="50" />
							<form:errors type="hidden" path="id" cssClass="error" />
				
						  </fildSet>
							<button type="submit" class="btn btn-success" name="opsirnije" >
								<b> Opsirnije </b>
							</button>				
						</form:form>
					</td>
					

				</tr>
				</c:forEach>
			</tbody>
		</table>
</div>

</body>
</html>