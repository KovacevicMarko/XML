<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" ui-sref="login">WS Project</a>
		</div>
		<ul class="nav navbar-nav">
			<li id="home" class="active"><a ui-sref="login">Login page</a></li>
		</ul>
		<form ng-submit="signin()" class="navbar-form navbar-right">
			<div class="form-group">
				<input type="text" ng-model="username" class="form-control" name="username" placeholder="Username">
			</div>
			<div class="form-group">
				<input type="password" ng-model="password" class="form-control" name="password" placeholder="Password">
			</div>
			<button type="submit" class="btn btn-default">Sign In</button>
		</form>
	</div>
</nav>