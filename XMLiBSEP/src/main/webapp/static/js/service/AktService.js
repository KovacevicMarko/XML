(function(){
	var app = angular.module("MyApp");
	app.service('AktService', function($http, $q){

		return {
			searchAkt : function (aktSearch, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/searchAkt",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {"tag" : aktSearch.tag, "sadrzaj" : aktSearch.sadrzaj}
		            }	

				$http(req).then(onSuccess, onError);
			}			
		
		};

	});

}());