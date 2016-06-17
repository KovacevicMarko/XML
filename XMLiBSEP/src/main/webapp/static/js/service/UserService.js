(function(){
	var app = angular.module("MyApp");
	app.service('UserService', function($http, $q){

		return {
			logIn: function(username, password, onSuccess, onError){
				
				
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/user/logIn/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {"username" : username, "password" : password}
		            }	

				$http(req).then(onSuccess, onError);
				//alert(username+"\n"+password)
				
				},
		    
		    register: function(onSuccess,onError,user){
					
		    	var req = {
		                method : "POST",
		                url: "/XMLiBSEP/user/register/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: user
		            }	

				$http(req).then(onSuccess, onError);
		    	
		    },
		    logOut : function (onSuccess,onError) {
		    	
		    }
		};

	});

}())

