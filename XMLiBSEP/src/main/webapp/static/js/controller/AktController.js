(function(){
	var app = angular.module("MyApp");
	
	var AktController = function($scope, AktService) {
		
		$scope.akt = {};
		
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
		
		$scope.searchAkt = function(){
			AktService.searchAkt(
				   $scope.aktSearch, 
				   onSuccess
				   ,onError);
	  		};
    }
	
	app.controller('AktController', AktController);

}());

