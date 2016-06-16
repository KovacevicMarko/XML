<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" ui-sref="main">XML & BSEP Project</a>
		</div>
		<ul class="nav navbar-nav">
			<li ng-class="{ active : isActive('main') }"><a ui-sref="main">Home</a></li>
			<li ng-if="user.role==true" ng-class="{ active : isActive('reports') }"><a ui-sref="reports">Reports</a></li>
			<li ng-if="user.uloga=='PREDSEDNIK'"><a ui-sref="sednica">Sednica</a></li>
			<li><a>Poslanici</a></li>
			<li><a>Amandmani</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><p class="navbar-text" style="color: white;">
					Welcome, <span style="color: yellow;"> {{ user.korisnickoIme}}</span>
				</p></li>
			<li><a href="" ng-click="signout()">Sign Out</a></li>
		</ul>
	</div>
</nav>