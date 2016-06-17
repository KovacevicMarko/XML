<div class="col-md-4">
		<h4><strong>Registracija</strong></h4>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><strong>Registracija korisnika</strong></h3>
			</div>
			
			<div class="panel-body">
				<form ng-submit="register()" role="form">
 					<div class="form-group">
						<label for="korisnickoIme">Korisnicko ime</label>
						<input type="text" ng-model="user.korisnickoIme"  class="form-control" id="korisni" placeholder="Username" required/>
					</div>
					<div class="form-group">
						<label for="lozinka">Lozinka</label>
						<input type="password" ng-model="user.lozinka" class="form-control" id="lozinka" placeholder="Password" required/>
					</div>
                    <div class="form-group">
						<label for="password2">Ponovite lozinku</label>
						<input type="password" ng-model="lozinka2" class="form-control" id="password2" placeholder="Repeat password" required/>
					</div>
					<div class="form-group">
						<label for="ime">Ime</label>
						<input type="text" ng-model="user.ime" class="form-control" id="ime" placeholder="Name" required/>
					</div>
					<div class="form-group">
						<label for="prezime">Prezime</label>
						<input type="text" ng-model="user.prezime" class="form-control" id="prezime" placeholder="Surname" required/>
					</div>
					<div class="form-group">
						<label for="email">Email</label>
						<input type="email" ng-model="user.email" class="form-control" id="email" placeholder="Email" required/>
					</div>
					
					<p align="center"><button type="submit" class="btn btn-sm btn-default">Register</button></p>
				</form>
  			</div>
		</div>
	</div>