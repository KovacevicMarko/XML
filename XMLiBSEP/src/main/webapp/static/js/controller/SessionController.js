(function() {
    var app = angular.module("MyApp");
	
	app.controller(
         
    'SessionController',function($scope,$rootScope, $state, SessionService,AktService) {
        
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
         
  		
  		var onGetSuccess = function(response) {
			$scope.predlozeniAktovi	= response.data.aktiPredlozeni ? response.data.aktiPredlozeni : [];
  		};
		
	  	$scope.getAkts = function () {
	  		AktService.getAkts(onGetSuccess, onError);
	  	}
  		
		 return $scope.authenticate();
    });
}());