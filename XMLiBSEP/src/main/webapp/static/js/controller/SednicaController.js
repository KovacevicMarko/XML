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
		
		
		var onSendArhivSuccess = function (response) {
			alert("Usvojen");
		}
		
		var onApproveSuccess = function(response) {
			var forArhiv = response.data;
			SednicaService.sendToArhiv(forArhiv, onSendArhivSuccess, onError);
			alert("Usvojen");
		}
		
		$scope.approve = function (akt) {
			SednicaService.approve(akt,onApproveSuccess, onError);
		}
	}

	app.controller('SednicaController', SednicaController);

}());
