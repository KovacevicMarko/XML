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
			},
			addAkt : function (akt, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/addAkt/",
		                headers: {
		                     'Content-Type': "application/xml"
		                         },
		                data: akt
		            }	

				$http(req).then(onSuccess, onError);
			},
			getAkts : function(onSuccess,onError) {
				var req = {
		                method : "GET",
		                url: "/XMLiBSEP/akt/",
		                headers: {
		                     'Content-Type': "application/json"
		                         }
		            }
				$http(req).then(onSuccess, onError);
			},
			withdraw : function (aktId, onSuccess, onError) {
				var req = {
		                method : "DELETE",
		                url: "/XMLiBSEP/akt/withdraw/",
		                headers: {
		                     'Content-Type': "text/plain"
		                         },
		                data : aktId+".xml"
		            }
				$http(req).then(onSuccess, onError);
			},
			getAktById : function (aktId, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/getAktById/",
		                headers: {
		                     'Content-Type': "text/html"
		                         },
		                data : aktId
		            }
				$http(req).then(onSuccess, onError);
			},
			generate:function(aktId, onSuccess, onError){

	            $http.post('/XMLiBSEP/akt/downloadAkt/'+aktId,{}, {responseType:'arraybuffer'})
	                .then(onSuccess,onError);

			},
			getAmandmansForAktId : function (aktId, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/getAmandmansForAktId/",
		                headers: {
		                     'Content-Type': "text/html"
		                         },
		                data : aktId
		            }
				$http(req).then(onSuccess, onError);
			}
		};

	});

}());