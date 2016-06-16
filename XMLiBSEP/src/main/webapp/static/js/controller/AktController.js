(function(){
	var app = angular.module("MyApp");
	
	var AktController = function($scope, $rootScope, AktService) {
		
		$scope.akts = [ {
			naziv : "naziv 1 akt",
			amandmani : [ {
				naziv : "naziv 1 amandman 1 akt"
			}, {
				naziv : "naziv 2 amandman 1 akt"
			} ]
		}, {
			naziv : "naziv 2 akt",
			amandmani : [ {
				naziv : "naziv 1 amandman 2 akt"
			} ]
		} ];
		
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
			
	  		
    }
	
	app.controller('AktController', AktController);

}());

