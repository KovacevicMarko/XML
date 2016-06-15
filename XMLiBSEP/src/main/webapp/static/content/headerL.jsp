<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" ui-sref="main">XML & BSEP Project</a>
		</div>
		<ul class="nav navbar-nav">
			<li id="home" ng-class="{ active : isActive('main') }"><a ui-sref="main">Home</a></li>
			<li id="projects" ng-class="{ active : isActive('projects') || isActive('project') || isActive('task') }"><a ui-sref="projects">Projects</a></li>
			<li ng-if="user.role==true" id="reports" ng-class="{ active : isActive('reports') }"><a ui-sref="reports">Reports</a></li>
			<li><a>Zakazane sednice</a></li>
			<li><a>Poslanici</a></li>
			<li><a>Amandmani</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><p class="navbar-text" style="color: white;">
					Welcome, <span style="color: yellow;"> {{ user.username }}</span>
				</p></li>
			<li><a href="" ng-click="signout()">Sign Out</a></li>
		</ul>
	</div>
</nav>