(function() {
	var app = angular.module('MyApp',["ui.router"]);
	
	app.run(function($rootScope) {
		$rootScope.root = "/XMLiBSEP";
	});
	
}());


