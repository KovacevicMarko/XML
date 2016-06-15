<div ng-include="'static/content/home_odb.jsp'"></div>

<div class="col-md-12" ng-controller="SednicaController">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Sednica</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Naziv akta</th>
						<th>Usvajanje akta</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="akt in akts">
						<td>{{akt.naziv}}</td>
						<td><input type="checkbox"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Naziv akta</th>
						<th>Naziv amandmana</th>
						<th>Usvajanje amandmana</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="amandman in amandmans">
						<td>{{amandman.akt.naziv}}</td>
						<td>{{amandman.naziv}}</td>
						<td><input type="checkbox"/></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>
</div>