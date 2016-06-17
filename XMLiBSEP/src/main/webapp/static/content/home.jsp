<div class="container" ng-controller="AktController" ng-init="getAkts()">
	<div class="col-md-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Svi akti</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th align="center" colspan="2">Usvojeni akti</th>
						</tr>
						<tr>
							<th>ID akta</th>
							<th>Detalji o aktu</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="akt in usvojeniAktovi">
							<td>{{akt.id}}</td>
							<td><button ng-click="getAktById(akt.id)"
									class="btn btn-info">Opsirnije</button>
								<button type="submit" class="btn btn-info" ng-click="generate(akt.id)">
									Generisi PDF</button></td>
						</tr>
						<tr>
							<th align="center" colspan="2">Predlozeni akti</th>
						</tr>
						<tr>
							<th>ID akta</th>
							<th>Detalji o aktu</th>
						</tr>
						<tr ng-repeat="akt in predlozeniAktovi">
							<td>{{akt.id}}</td>
							<td><button ng-click="getAktById(akt.id)"
									class="btn btn-info">Opsirnije</button>
								<button type="submit" class="btn btn-info" ng-click="generate(akt.id)">
									Generisi PDF</button></td>
						</tr>
					</tbody>
				</table>
				
				<div ng-bind-html="aktHTML"></div>

	<div ng-show="showPdf" style="width: 100%; height: 600px;">
		<embed ng-src="{{content}}" style="width: 100%; height: 600px"></embed>
	</div>
			</div>
		</div>
	</div>


	<div>
		<div >
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<strong>Pretrazivanje akata</strong>
						</h3>
					</div>

					<div class="panel-body">
						<form ng-submit="searchAkt()">
							<div class="form-group">
								<label>Filter po tagu (opciono):</label> <select
									ng-model="aktSearch.tag">
									<option></option>
									<option value="Clan">Clan</option>
									<option value="Stav">Stav</option>
									<option value="Tacka">Tacka</option>
									<option value="Alineja">Alineja</option>
								</select>
							</div>

							<div class="form-group">
								<label for="sadrzaj">Sadrzaj:</label><br />
								<textarea id="sadrzaj" ng-model="aktSearch.sadrzaj" rows="5"
									cols="50"></textarea>

							</div>
							<input type="submit" class="btn btn-info" />
						</form>

						<div>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>ID akta</th>
										<th>Detalji</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="akt in filtriraniAktovi">
										<td>{{akt}}</td>
										<td><button ng-click="getAktById(akt)"
												class="btn btn-info">Opsirnije</button></td>
									</tr>
								</tbody>
							</table>
							<div ng-bind-html="aktHTML"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div>
		<div >
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<strong>Pretrazivanje akata po referenci</strong>
						</h3>
					</div>
					
					<div class="form-group">
      					  <label for="chooseProject">Choose project:</label>
        						<select ng-change="getReferences()" id="chooseProject" ng-options="a.nazivAkt for a in usvojeniAktovi" ng-model="p">
         						   <option></option>    
        						</select>    
   					 </div>
   					 
   					 <div>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>ID akta</th>
										<th>Detalji</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="aktId in referencedActs">
										<td>{{aktId}}</td>
										<td><button ng-click="getAktById(akt)"
												class="btn btn-info">Opsirnije</button></td>
									</tr>
								</tbody>
							</table>
						</div>
				</div>
			</div>
		</div>		
	</div>									

</div>

<!-- <div ng-if="user.uloga=='ODBORNIK' || user.uloga=='PREDSEDNIK'" ng-include="'static/content/home_odb.jsp'"></div>-->