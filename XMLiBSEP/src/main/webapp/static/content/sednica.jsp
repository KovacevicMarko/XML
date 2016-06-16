<div class="col-md-12" ng-controller="SednicaController">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Sednica</h3>
		</div>
		<div class="panel-body">
			<button ng-show="sednicaStarted==false" ng-click="startSednica()"
				class="btn btn-info">Zapocni sednicu</button>
			<button ng-show="sednicaStarted==true" ng-click="endSednica()"
				class="btn btn-success">Zavrsi sednicu</button>
			<div ng-show="sednicaStarted==true">
				<table class="table table-condensed"
					style="border-collapse: collapse;">
					<thead>
						<tr>
							<th>Naziv akta</th>
							<th>Usvajanje akta</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat-start="akt in akts"class="accordion-toggle">
							<td data-toggle="collapse"
							data-target="#aman{{$index}}" style="cursor:pointer">{{akt.naziv}}</td>
							<td><input type="checkbox" /></td>
						</tr>
						<tr ng-repeat-end>
							<td colspan="2" class="hiddenRow">
								<div id="aman{{$index}}" class="accordion-body collapse">
								
								<table
									class="table table-condensed">
									<thead>
										<tr>
											<th>Naziv amandmana</th>
											<th>Usvajanje amandmana</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="amandman in akt.amandmani">
											<td>{{amandman.naziv}}</td>
											<td><input type="checkbox" /></td>
										</tr>
									</tbody>
								</table>
								</div>
							</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>