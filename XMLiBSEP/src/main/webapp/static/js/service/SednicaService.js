(function(){
	var app = angular.module("MyApp");
	app.service('SednicaService', function($http, $q){

		return {
			/*
			searchAmandman : function (amandmanSearch, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/searchAmandman",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {"tag" : amandmanSearch.tag, "sadrzaj" : amandmanSearch.sadrzaj}
		            }	

				$http(req).then(onSuccess, onError);
			}			
		*/
		};

	});

}());