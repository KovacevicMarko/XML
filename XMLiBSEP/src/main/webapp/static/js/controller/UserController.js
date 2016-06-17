(function(){
	var app = angular.module("MyApp");
	
	var UserController = function($scope, $rootScope, $state, UserService) {
        
		
	  	
	  	var onRegisterSuccess = function(response) {
	  		$rootScope.user = response.data;
	  		$state.go('main');
	  	}
	  	
	  	var onError = function(response){
	  		alert('Error');
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

