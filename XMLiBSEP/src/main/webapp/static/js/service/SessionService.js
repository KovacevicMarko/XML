(function(){
	var app = angular.module("MyApp");
	app.service('SessionService', function($http, $q){

		return {
			authenticate : function (onSuccess, onError) {
				var req = {
		                method : "GET",
		                url: "/XMLiBSEP/user/checkSession/",
		                headers: {
		                     'Content-Type': "application/json"
		                         }
		            }	

				$http(req).then(onSuccess, onError);
			}			
		
		};

	});

}());