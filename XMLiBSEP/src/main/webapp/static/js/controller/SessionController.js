(function() {
    var app = angular.module("MyApp");
	
	app.controller(
         
    'SessionController',function($scope,$rootScope, $state, SessionService,UserService) {
        
		var onSuccess = function(response){	  
			console.log(response.data);
			if(response.data){
				
				$rootScope.user = response.data;
				if ($state.current.name == "login") {
					$state.go('main');
				}
			}else{
				
                $state.go('login');
			}

		};
		
		var onError = function(response){
			console.log(response.data);
			
		}
			   
        $scope.authenticate = function(){

        	SessionService.authenticate(
			   onSuccess
			   ,onError);
  		};
           	
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
	  	
	  	
		 return $scope.authenticate();
    });
}());