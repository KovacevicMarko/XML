(function(){
	var app = angular.module("MyApp");
	
	var UserController = function($scope, $rootScope, $state, UserService) {
        
		
		var onSuccess = function(response){	  
			console.log(response.data);
			if(response.data){
				$rootScope.user = response.data;
				if ($state.current.name == "login") {
					$state.go('main');
				}
			}else{
				delete $scope.username;
				delete $scope.password;
				alert(response.data);				
			}
		};
		
		var onError = function(response){
			console.log(response.data);
			//alertify.error("ERROR");
		}
		
		$scope.logIn = function(){
			UserService.logIn(
				   $scope.username, 
				   $scope.password, 
				   onSuccess
				   ,onError);
	  	};
	  	
	  	var onLogOutSuccess = function(response) {
	  		$rootScope.user = null;
	  		$state.go('login');
	  	}
	  	
	  	$scope.logOut = function() {
	  		UserService.logOut(onLogOutSuccess,onError);
	  	}
	  	
	  	var onRegisterSuccess = function(response) {
	  		$rootScope.user = response.data;
	  		$state.go('main');
	  	}
	  	
	  	$scope.register = function(){
	  		
	  		if($scope.lozinka2!==$scope.user.lozinka){
	  			alert("Niste ponovili lozinku");
	  		}
	  		else if($scope.user.lozinka.length < 8){
	  			alert("Lozinka mora imati minimum 8 karaktera");
	  		}
	  		else{
	  			$scope.user.uloga = "ODBORNIK";
	  			UserService.register(onRegisterSuccess,onError,$scope.user);
	  		}
	  	}
	  	
	  	
    }
	
	app.controller('UserController', UserController);

}());

