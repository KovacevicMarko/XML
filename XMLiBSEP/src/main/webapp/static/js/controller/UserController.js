(function(){
	var app = angular.module("MyApp");
	
	var UserController = function($scope, UserService) {
        
		
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
		
		$scope.signin = function(){
			UserService.signin(
				   $scope.username, 
				   $scope.password, 
				   onSuccess
				   ,onError);
	  		};
    }
	
	app.controller('UserController', UserController);

}());

