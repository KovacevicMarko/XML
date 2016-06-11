<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/TagLibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Skupstina grada novog sada</title>

		
<script src="/js/jquery.min.js"></script>	
<script src="/js/bootstrap.js"/></script>





</head>
<body>



<c:if test="${not empty porukaOIsteku}">
		<strong>Neuspesno registrovanje!</strong> ${porukaOIsteku}						
</c:if>	




<div align="left" class="col-xs-3">
		<form action="./PretragaRestoranaController" method="post" class="dodavanjeStavke" accept-charset="ISO-8859-1">
		<table><td><input required class="form-control" placeholder="Pretrazite akte..." type="text" name="pretragaAkata" ></td>  <td> 	<input type="submit" class="btn btn-primary" name="submit" value="Pretrazi"></td></table>
		</form>
</div>

<div align="center" class="col-xs-3">
	<table  align="center" class="table table-striped table-bordered table-hover table-condensed">
			<caption style="border: inherit; background-color: lightgrey;">Akti....</caption>
			<thead class="thead-inverse">
				
					<th>Naziv</th>
					<th>Delovi</th>
					<th>Preambula</th>
					<th>Opsirnije</th>
				
			</thead>
			<tbody>
				<c:forEach items="${akti}" var="akt">
				<tr>
					<td>${akt.naziv}</td>
					<td>
						<c:url var="action" value="/deo" />
						<form:form id="formFindDeo" action="${action}" method="post" modelAttribute="deo">
			
						  <fildSet>	
							<form:input type="hidden" path="id" value="${akt.ID}" rows="5" cols="50" />
							<form:errors type="hidden" path="id" cssClass="error" />
				
						  </fildSet>
							<button type="submit" class="btn btn-success" name="save" >
								<b> Delovi </b>
							</button>				
						</form:form>
					</td>
					<td>${akt.preambula}</td>
					<td>
						<c:url var="action" value="/deo" />
						<form:form id="formFindDeo" action="${action}" method="post" modelAttribute="deo">
			
						  <fildSet>	
							<form:input type="hidden" path="id" value="${akt.ID}" rows="5" cols="50" />
							<form:errors type="hidden" path="id" cssClass="error" />
				
						  </fildSet>
							<button type="submit" class="btn btn-success" name="save" >
								<b> Delovi </b>
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