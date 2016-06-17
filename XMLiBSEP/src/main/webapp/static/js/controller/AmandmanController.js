(function(){
	var app = angular.module("MyApp");
	
	var AmandmanController = function($scope, $rootScope, AmandmanService) {
		
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
		
		$scope.searchAmandman = function(){
			AmandmanService.searchAmandman(
				   $scope.amandmanSearch, 
				   onSuccess
				   ,onError);
	  	};
	  	
	  	$scope.addAmandman = function () {
	  		AmandmanService.addAmandman($scope.amandmanToAdd, 
				   onSuccess
				   ,onError);
	  	}
	  	

		$scope.clearInputs = function () {
			delete $scope.amandmanToAdd;
		}
    }
	
	app.controller('AmandmanController', AmandmanController);

}());

