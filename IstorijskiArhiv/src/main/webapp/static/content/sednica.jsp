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
						<tr ng-repeat-start="akt in predlozeniAktovi"class="accordion-toggle">
							<td>{{akt.nazivAkt}}</td>
							<td>
							<!-- <input ng-init="usvojeniAktovi['akt_'+akt.id] = false" ng-model="usvojeniAktovi['akt_'+akt.id]"type="checkbox" /> -->
									<button data-toggle="collapse"
							data-target="#aman{{$index}}" class="btn btn-info">Amandmani</button>
							<span style="float:right">
								<button ng-click="approve(akt)" class="btn btn-info">Usvajanje</button>
								<button class="btn btn-danger">Odbijanje</button>
							</span>
							</td>
						</tr>
						<tr ng-repeat-end>
							<td colspan="2" class="hiddenRow">
								<div id="aman{{$index}}" class="accordion-body collapse">
								
								<table
									class="table table-condensed">
									<thead>
										<tr>
											<th>Pravni osnov amandmana</th>
											<th>Usvajanje amandmana</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="amandman in akt.amandmani">
											<td>{{amandman.pravniOsnov}}</td>
											<td><input ng-init="usvojeniAmandmani['amandman_'+amandman.id] = false" ng-model="usvojeniAmandmani['amandman_'+amandman.id]" type="checkbox" /></td>
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