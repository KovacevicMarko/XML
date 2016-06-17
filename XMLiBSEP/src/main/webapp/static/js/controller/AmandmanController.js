(function(){
	var app = angular.module("MyApp");
	
	var AmandmanController = function($scope, $rootScope, $sce, AmandmanService) {
		
		$scope.amandmanSearch = {};
		
		var onSuccess = function(response){	  
			console.log(response.data);
			/*if(response.data.success==true){
				$scope.user = response.data.user;
				$state.go('main');
			}else{
				delete $scope.username;
				delete $scope.password;
				alert(response.data.msg);
				
			}
			*/
		};
		
		var onError = function(response){
			console.log(response.data);
			//alertify.error("ERROR");
		}

		$scope.clearInputs = function() {
			delete $scope.amandmanToAdd;
		}
		$scope.searchAmandman = function(){
			AmandmanService.searchAmandman(
				   $scope.amandmanSearch, 
				   onSuccess
				   ,onError);
	  	};
	  	
	  	var onAddSuccess = function(response) {
			//$rootScope.predlozeniAktovi.push(response.data);
			$scope.clearInputs();
			$("#ModalAddAmandman").modal('hide');
		};
	  	
	  	$scope.addAmandman = function () {
	  		AmandmanService.addAmandman($scope.amandmanToAdd, 
				   onSuccess
				   ,onError);
	  	}
	  	
	  	var onGetAmandmanSuccess = function(response) {
	  		$scope.amandmanHTML = response.data.amandman;
	  	}
	  	
	  	$scope.getAmandmanById = function(idAmandman) {
	  		AmandmanService.getAmandmanById(idAmandman, onGetAmandmanSuccess, onError);
	  	}
	  	
	  	$scope.generateAmandmanPdf = function (idAmandman) {
	  		AmandmanService.generateAmandmanPdf(idAmandman, function(response) {
				console.log("ODGOVOR SA PDF-OM");
				console.log(response);
				var file = new Blob([ response.data ], {
					type : 'application/pdf'
				});
				var fileURL = URL.createObjectURL(file);
				$scope.showPdf = true;
				$scope.content = $sce.trustAsResourceUrl(fileURL);
			}, function(response) {

			});
	  	}
	  	
	  	var onWithdrawSuccess = function(response) {
	  		//var index = $rootScope.predlozeniAktovi.indexOf($scope.aktToDeleteAmandFrom);
	  		
	  		//$rootScope.predlozeniAktovi.splice($scope.amandIndexToDel,1);
	  	}
	  	
	  	$scope.withdrawAmandman = function (id) {
	  		//$scope.aktToDeleteAmandFrom = aktId;
	  		AmandmanService.withdrawAmandman(id, onWithdrawSuccess, onError);
	  	}
	  	
		$scope.clearInputs = function () {
			delete $scope.amandmanToAdd;
		}
    }
	
	app.controller('AmandmanController', AmandmanController);

}());

