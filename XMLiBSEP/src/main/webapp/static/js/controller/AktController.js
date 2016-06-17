(function() {
	var app = angular.module("MyApp");

	var AktController = function($scope, $rootScope, $sce, $window, AktService) {

		$rootScope.predlozeniAktovi = [];
		$scope.aktHTML = "";
		$scope.filtriraniAktovi = [];
		
		var onSuccess = function(response) {
			console.log(response.data);
		}
		
		var onFilterSuccess = function(response) {
			var data = response.data;
			for (var id in data) {
				$scope.filtriraniAktovi.push(id.substring(0,id.length-4));
			}
				
			/*if(response.data.success==true){
				$scope.user = response.data.user;
				$state.go('main');
			}else{
				delete $scope.username;
				delete $scope.password;
				alert(response.data.msg);
				
			}
			 */
		};

		var onError = function(response) {
			console.log(response.data);
			//alertify.error("ERROR");
		}

		$scope.searchAkt = function() {
			if ($scope.aktSearch.tag == null) {
				AktService.searchAkt($scope.aktSearch.sadrzaj, onFilterSuccess,
						onError);
			} else {
				AktService.searchAktByTag($scope.aktSearch, onFilterSuccess, onError);
			}
		};
		
		var onGetReferencesSuccess = function(response){
			alert("Usao");
			$scope.referencedActs = response.data;
		}
		
		$scope.getReferences = function(){
			AktService.getReferences(onGetReferencesSuccess,onError,$scope.p.id);
		}
		
		var onAddSuccess = function(response) {
			$rootScope.predlozeniAktovi.push(response.data);
			$scope.clearInputs();
			$("#ModalAddAkt").modal('hide');
		};

		$scope.addAkt = function() {
			AktService.addAkt($scope.aktToAdd, onAddSuccess, onError);
		}

		$scope.clearInputs = function() {
			delete $scope.aktToAdd;
		}

		var onGetSuccess = function(response) {
			$rootScope.predlozeniAktovi = response.data.aktiPredlozeni ? response.data.aktiPredlozeni
					: [];
			
			var tmpf = function (i) {
				var id = $rootScope.predlozeniAktovi[i].id;
				AktService.getAmandmansForAktId(id,function(response1) {
					$rootScope.predlozeniAktovi[i].amandmani = response1.data;
				},function(response2){
					
				});
			}
			
			for (var i = 0; i < $rootScope.predlozeniAktovi.length; i+=1) {
				tmpf(i);
			}
			
			$rootScope.usvojeniAktovi = response.data.aktiUsvojeni ? response.data.aktiUsvojeni
					: [];
		};

		$scope.getAkts = function() {
			AktService.getAkts(onGetSuccess, onError);

		}

		$scope.withdrawAkt = function(idAkt) {
			AktService.withdraw(idAkt, function(response) {
				//alert(response.data);
				$rootScope.predlozeniAktovi = response.data.akti;
				//$window.location.reload();
			}, onError);
		}

		var onGetAktSuccess = function(response) {
			$scope.aktHTML = response.data.akt;
		};

		$scope.getAktById = function(idAkt) {
			AktService.getAktById(idAkt, onGetAktSuccess, onError);
		}

		$scope.generateAktPdf = function(id) {

			AktService.generateAktPdf(id, function(response) {
				console.log("ODGOVOR SA PDF-OM");
				console.log(response);
				var file = new Blob([ response.data ], {
					type : 'application/pdf'
				});
				var fileURL = URL.createObjectURL(file);
				$scope.showPdf = true;
				$scope.content = $sce.trustAsResourceUrl(fileURL);
			}, function(response) {

			});
		};

	}

	app.controller('AktController', AktController);

}());
