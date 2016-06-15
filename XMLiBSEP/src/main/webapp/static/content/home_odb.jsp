<div ng-include="'static/content/home_gra.jsp'"></div>

<br />
<br />
<br />

<div class="col-md-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Predlozeni akti</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID akta</th>
						<th>Naziv akta</th>
						<th>Novi amandman</th>
						<th>Povlacenje akta</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="akt in akts">
						<td>{{akt.id}}</td>
						<td>{{akt.naziv}}</td>
						<td><button class="btn btn-success">Predlozi
								amandman na akt</button></td>
						<td><button ng-show="true" class="btn btn-success">Povuci
								akt</button></td>
					</tr>
				</tbody>
			</table>
			<button class="btn btn-info">Predlozi novi akt</button>
		</div>

	</div>

</div>
<div class="col-md-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Predlozeni amandmani</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Naziv akta</th>
						<th>Naziv amandmana</th>
						<th>Povlacenje amandmana</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="amandman in amandmans">
						<td>{{amandman.akt.naziv}}</td>
						<td>{{amandman.naziv}}</td>
						<td><button ng-show="true" class="btn btn-danger">Povuci
								amandman</button></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>
</div>
