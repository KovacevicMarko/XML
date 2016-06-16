(function(){
	var app = angular.module("MyApp");
	app.service('AktService', function($http, $q){

		return {
			searchAkt : function (sadrzaj, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/search/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: sadrzaj
		            }	

				$http(req).then(onSuccess, onError);
			},
			searchAktByTag : function (aktSearch, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/searchByTag/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {"tagName" : aktSearch.tag, "content" : aktSearch.sadrzaj}
		            }	

				$http(req).then(onSuccess, onError);
			}
		};

	});

}());