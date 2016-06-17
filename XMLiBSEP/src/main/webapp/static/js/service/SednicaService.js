(function(){
	var app = angular.module("MyApp");
	app.service('SednicaService', function($http, $q){

		return {
			approve : function(akt, usvojeniAmandmani, onSuccess, onError) {
				
				var listaUsvojenih = [];
				var number = 0;
				if (akt.amandmani) {
					number = akt.amandmani.length;
					for (var i = 0; i < akt.amandmani.length; i++) {
						var amandmanId = akt.amandmani[i].id;
						if (usvojeniAmandmani['amandman_'+amandmanId]) {
							listaUsvojenih.push(id+".xml");
						}
					}
				}
				
				
				
				var req = {
		                method : "POST",
		                url: "/XMLiBSEP/akt/approve/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: {aktId : akt.id, amandmanIds : listaUsvojenih, numberOfAmandmans:number }
		            }	

				$http(req).then(onSuccess, onError);
			},
			sendToArhiv : function (forArhiv, onSuccess, onError) {
				var req = {
		                method : "POST",
		                url: "/IstorijskiArhiv/storeAkt/",
		                headers: {
		                     'Content-Type': "application/json"
		                         },
		                data: forArhiv
		            }	

				$http(req).then(onSuccess, onError);
			}
		};

	});

}());