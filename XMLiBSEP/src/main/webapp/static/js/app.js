(function() {
	var app = angular.module('MyApp',["ui.router", 'ngSanitize']);
	
	app.run(function($rootScope) {
		$rootScope.root = "/XMLiBSEP";
	});
	
}());


