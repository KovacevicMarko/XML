(function() {
    var app = angular.module("MyApp");
	
	app.controller(
         
    'SessionController',function($scope,$rootScope, $state, SessionService) {
        
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
         
		 return $scope.authenticate();
    });
}());