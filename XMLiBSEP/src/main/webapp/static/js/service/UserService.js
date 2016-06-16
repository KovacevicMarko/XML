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
			fetchAllUsers: function() {
					return $http.get('/XMLiBSEP/user/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
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
		    
		    updateUser: function(user, id){
					return $http.put('/XMLiBSEP/user/'+id, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
			    
				/*deleteUser: function(id){
						return $http.delete('http://localhost:8080/Spring4MVCAngularJSExample/user/'+id)
								.then(
										function(response){
											return response.data;
										}, 
										function(errResponse){
											console.error('Error while deleting user');
											return $q.reject(errResponse);
										}
								);
				}*/
			
		};

	});

}())

