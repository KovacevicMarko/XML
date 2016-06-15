<!-- 
<div class="col-md-4">
	<h4><strong>User Registration</strong></h4>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><strong>Insert necessary information</strong></h3>
		</div>
		
		<div class="panel-body">
			<form ng-submit="signup()" role="form">
					<div class="form-group">
					<label for="username">Username</label>
					<input type="text" ng-model="username"  class="form-control" id="username" placeholder="Username">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" ng-model="password" class="form-control" id="password" placeholder="Password">
				</div>
                   <div class="form-group">
					<label for="password2">Repeat password</label>
					<input type="password" ng-model="password2" class="form-control" id="password2" placeholder="Repeat password">
				</div>
				<p align="center"><button type="submit" class="btn btn-sm btn-default">Sign Up</button></p>
			</form>
 			</div>
	</div>
</div>


<div align="center" class="col-xs-3">
	<table  align="center" class="table table-striped table-bordered table-hover table-condensed">
			<caption style="border: inherit; background-color: lightgrey;">Akti....</caption>
			<thead class="thead-inverse">
				
					<th>Naziv</th>
					<th>Predlagac</th>
					<th>Preambula</th>
					<th>Opsirnije</th>
				
			</thead>
			<tbody>
				<c:if test="${akti != null}">
				<c:forEach items="${akti}" var="akt">
				<tr>
					<td>${akt.naziv}</td>
					<td>${akt.prelazneIZavrsneOdredbe.predlagac.ime} ${akt.prelazneIZavrsneOdredbe.predlagac.prezime}</td>
					<td>${akt.preambula}</td>
					<td>
						<c:url var="action" value="/akt" />
						<form:form id="formFindDeo" action="${action}" method="post" modelAttribute="aktId">
			
						  <fildSet>	
							<form:input type="hidden" path="id" value="${akt.ID}" rows="5" cols="50" />
							<form:errors type="hidden" path="id" cssClass="error" />
				
						  </fildSet>
							<button type="submit" class="btn btn-success" name="opsirnije" >
								<b> Opsirnije </b>
							</button>				
						</form:form>
					</td>
					

				</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
</div>
 -->
 <div ng-controller="AktController">
 	<div ng-include="'static/content/home_gra.jsp'"></div>
 </div>