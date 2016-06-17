(function() {
	var app = angular.module("MyApp");

	var SednicaController = function($scope,$rootScope, SednicaService) {

		$scope.sednicaStarted = false;

		$scope.startSednica = function() {
			$scope.sednicaStarted = true;
		}

		$scope.endSednica = function() {
			$scope.sednicaStarted = false;
		}
	}

	app.controller('SednicaController', SednicaController);

}());
