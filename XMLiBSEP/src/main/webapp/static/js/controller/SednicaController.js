(function() {
	var app = angular.module("MyApp");

	var SednicaController = function($scope,$rootScope, SednicaService) {

		$scope.sednicaStarted = false;

		$scope.akts = [ {
			naziv : "naziv 1 akt",
			amandmani : [ {
				naziv : "naziv 1 amandman 1 akt"
			}, {
				naziv : "naziv 2 amandman 1 akt"
			} ]
		}, {
			naziv : "naziv 2 akt",
			amandmani : [ {
				naziv : "naziv 1 amandman 2 akt"
			} ]
		} ];

		$scope.startSednica = function() {
			$scope.sednicaStarted = true;
		}

		$scope.endSednica = function() {
			$scope.sednicaStarted = false;
		}
	}

	app.controller('SednicaController', SednicaController);

}());
