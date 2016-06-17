(function(){
	var app = angular.module("MyApp");
	app.service('SednicaService', function($http, $q){

		return {
			approve : function(akt, usvojeniAmandmani, onSuccess, onError) {
				
				var listaUsvojenih = [];
				
				for (var i = 0; i < akt.amandmani.length; i++) {
					var amandmanId = akt.amandmani[i].id;
					if (usvojeniAmandmani['amandman_'+id]) {
						listaUsvojenih.push(id+".xml");
					}
				}
				
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/approve/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {aktId : akt.id, amandmanIds : listaUsvojenih, numberOfAmandmans:akt.amandmani.length }
		            }	

				$http(req).then(onSuccess, onError);
			},
			sendToArhiv : function (forArhiv, onSendArhivSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/IstorijskiArhiv/saveAkt/",
		                headers: {
		                     'Content-Type': "application/xml"
		                         },
		                data: forArhiv
		            }	

				$http(req).then(onSuccess, onError);
			}
		};

	});

}());