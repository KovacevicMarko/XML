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
		    
		    createUser: function(user){
					return $http.post('/XMLiBSEP/user/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    logOut : function (onSuccess,onError) {
		    	
		    }
		};

	});

}())

