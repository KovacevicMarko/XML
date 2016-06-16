(function() {
	
			
    var app = angular.module("MyApp");
         
    var MainController = function($scope, $rootScope, $state) {
        
		
		/*
        var onSuccess = function(response){	  
			console.log(response.data);
			if(response.data.success==true){
				//alertifyy.success("WELCOME!");
				$rootScope.user = response.data.user;
			}else{
				//alertifyy.error("ERROR");
                $state.go('login');
			}

		};
		
		var onError = function(response){
			console.log(response.data);
			//alertifyy.error("ERROR");
		}
        
          //AuthService.authenticate(onSuccess,onError);*/
    }

    app.controller("MainController",MainController);
    
    
}());