(function(){
	var app = angular.module("MyApp");
	
	var AktController = function($scope, $rootScope, $window,AktService) {
		
		$scope.predlozeniAktovi	=[];
		
		var onSuccess = function(response){	  
			console.log(response.data);
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
		
		var onError = function(response){
			console.log(response.data);
			//alertify.error("ERROR");
		}
		
		$scope.searchAkt = function(){
			if ($scope.aktSearch.tag == null) {
				AktService.searchAkt(
						   $scope.aktSearch.sadrzaj, 
						   onSuccess
						   ,onError);
			}
			else {
				AktService.searchAktByTag(
						   $scope.aktSearch, 
						   onSuccess
						   ,onError);
			}
		};
		var onAddSuccess = function(response) {
			$scope.predlozeniAktovi.push(response.data);
			$scope.clearInputs();
			$("#ModalAddAkt").modal('hide');
		};
		
		$scope.addAkt = function() {
			AktService.addAkt($scope.aktToAdd, onAddSuccess,onError);
		}
		
		$scope.clearInputs = function () {
			delete $scope.aktToAdd;
		}
		
		
		var onGetSuccess = function(response) {
			$scope.predlozeniAktovi	= response.data.aktiPredlozeni ? response.data.aktiPredlozeni : [];
			$scope.usvojeniAktovi =  response.data.aktiUsvojeni ? response.data.aktiUsvojeni : [];
  		};
		
	  	$scope.getAkts = function () {
	  		AktService.getAkts(onGetSuccess, onError);
	  		
	  	}
	  	
	  	$scope.withdrawAkt = function (idAkt) {
	  		AktService.withdraw(idAkt,function(response){
	  			//alert(response.data);
	  			$scope.predlozeniAktovi = response.data.akti;
	  			//$window.location.reload();
	  		},onError);
	  	}
	  	
	  	var onGetAktSuccess = function (response) {
	  		console.log(response.data);
	  	};
	  	
	  	$scope.getAktById = function(idAkt) {
	  		AktService.getAktById(idAkt,onGetAktSuccess, onError);
	  	}
    }
	
	app.controller('AktController', AktController);

}());

