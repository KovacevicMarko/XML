(function(){
	var app = angular.module("MyApp");
	app.service('AmandmanService', function($http, $q){

		return {
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
			},
			addAmandman : function (amandman, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/amandman/addAmandman/",
		                headers: {
		                     'Content-Type': "application/xml"
		                         },
		                data: amandman
		            }	

				$http(req).then(onSuccess, onError);
			},
			getAmandmanById : function (amandmanId, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/amandman/getAmandmanById/",
		                headers: {
		                     'Content-Type': "text/html"
		                         },
		                data : amandmanId
		            }
				$http(req).then(onSuccess, onError);
			},
			generateAmandmanPdf:function(amandmanId, onSuccess, onError){

	            $http.post('/XMLiBSEP/amandman/downloadAmandman/'+amandmanId,{}, {responseType:'arraybuffer'})
	                .then(onSuccess,onError);

			},
			withdrawAmandman : function (amandmanId, onSuccess, onError) {
				var req = {
		                method : "DELETE",
		                url: "/XMLiBSEP/amandman/withdraw/",
		                headers: {
		                     'Content-Type': "text/plain"
		                         },
		                data : amandmanId
		            }
				$http(req).then(onSuccess, onError);
			}
		};

	});

}());