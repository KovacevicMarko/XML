

<br />
<br />
<br />
<div ng-controller="AktController">
	<div class="col-md-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Predlozeni akti</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Naziv akta</th>
							<th>Povlacenje akta</th>
							<th>Prikaz amandmana na ovom aktu</th>
							<th>Detalji o aktu</th>
						</tr>
					</thead>
					<tbody ng-init="getAkts()">
						<tr ng-repeat-start="akt in predlozeniAktovi">


							<td>{{akt.nazivAkt}}</td>
							<td><button ng-click="withdrawAkt(akt.id)" ng-show="true"
									class="btn btn-danger">Povuci akt</button></td>
							<td><button class="btn btn-default accordion-toggle"
									data-toggle="collapse" data-target="#aman{{$index}}">Amandmani</button></td>
							<td><button ng-click="getAktById(akt.id)" class="btn btn-info">Opsirnije</button></td>
						</tr>
						<tr ng-repeat-end>
							<td colspan="4" class="hiddenRow">
								<div id="aman{{$index}}" class="accordion-body collapse">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Naziv amandmana</th>
												<th>Povlacenje amandmana</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="amandman in akt.amandmani">
												<td>{{amandman.naziv}}</td>
												<td><button ng-show="true" class="btn btn-danger">Povuci
														amandman</button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
						</tr>

					</tbody>
				</table>
				<button class="btn btn-info" data-toggle="modal"
					data-target="#ModalAddAkt">Predlozi novi akt</button>
				<button class="btn btn-info" data-toggle="modal"
					data-target="#ModalAddAmandman">Predlozi novi amandman</button>
			</div>

		</div>

	</div>






	<div class="modal fade" id="ModalAddAkt" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<form ng-submit="addAkt()" class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Predlozi novi akt</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label for="sadrzaj">Sadrzaj akta</label>
						<textarea rows="20" cols="50" ng-model="aktToAdd"
							class="form-control" id="sadrzaj" placeholder="Sadrzaj"
							ng-required="true"></textarea>
					</div>

				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-success" value="OK">
					<button ng-click="clearInputs()" type="button"
						class="btn btn-danger" data-dismiss="modal">Cancel</button>
				</div>
			</form>
		</div>
	</div>

</div>

<div ng-controller="AmandmanController">

	<div class="col-md-6">
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

	<div class="modal fade" id="ModalAddAmandman" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<form ng-submit="addAmandman()" ng-controller="AmandmanController"
				class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Predlozi novi amandman</h4>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label for="sadrzaj">Sadrzaj amandmana</label>
						<textarea rows="20" cols="50" ng-model="amandmanToAdd"
							class="form-control" id="sadrzaj" placeholder="Sadrzaj"
							ng-required="true"></textarea>
					</div>

				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-success" value="OK">
					<button ng-click="clearInputs()" type="button"
						class="btn btn-danger" data-dismiss="modal">Cancel</button>
				</div>
			</form>
		</div>
	</div>
</div>