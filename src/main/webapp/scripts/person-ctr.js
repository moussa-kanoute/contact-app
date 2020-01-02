app.controller('PersonCRUDCtrl', ['$scope', '$routeParams', '$interval', 'PersonCRUDService', function ($scope, $routeParams, $interval, PersonCRUDService) {

	$scope.updatePerson = function () {
		PersonCRUDService.updatePerson($scope.person.id, $scope.person.firstName, $scope.person.lastName, 
				$scope.person.fullName, $scope.person.address, $scope.person.email, $scope.person.mobileNumber)
			.then(function success(response){
				$scope.message = 'Person data updated!';
				$scope.errorMessage = '';
			},
			function error(response){
				$scope.errorMessage = 'Error updating person!';
				$scope.message = '';
			});
		}
    
    $scope.getPerson = function (personId) {
        PersonCRUDService.getPerson(personId)
        	.then(function success(response){
        		$scope.person = response.data;
        		$scope.message='';
        		$scope.errorMessage = '';
        	},
        	function error (response ){
        		$scope.message = '';
        		if (response.status === 404){
        			$scope.errorMessage = 'Person not found!';
        		}
        		else {
        			$scope.errorMessage = "Error getting person!";
        		}
          }
        );
    }
    
    $scope.addPerson = function () {
    	if ($scope.person != null && $scope.person.firstName) {
            PersonCRUDService.addPerson($scope.person.firstName, $scope.person.lastName, $scope.person.fullName, 
            		$scope.person.address, $scope.person.email, $scope.person.mobileNumber)
              .then (function success(response){
            	  $scope.message = 'Person added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding person!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please review person details!';
            $scope.message = '';
        }
    }
    
    $scope.initData = function (personId) {
    	var parsedId = Number.parseInt(personId);
    	if(Number.isInteger(parsedId) && parsedId > 0){
    		$scope.getPerson(parsedId);
    		$('#addAction').hide();
    		$('#updateAction').show();
    	} else {
    		$('#addAction').show();
    		$('#updateAction').hide();
    	}
    	$('#idPersInput').prop('disabled', true);
    }

    $scope.initData($routeParams.personId);

}]);

app.service('PersonCRUDService',['$http', function ($http) {
	
    this.getPerson = function getPerson(personId){
    	var parsedId = Number.parseInt(personId);
    	if(Number.isInteger(parsedId)){
    		return $http({
    			method: 'GET',
    			url: 'persons/'+ parsedId
    		});
    	}
	}
	
    this.addPerson = function addPerson(firstName, lastName, fullName, address, email, mobileNumber){
        return $http({
          method: 'POST',
          url: 'persons',
          data: {firstName:firstName, lastName:lastName, fullName:fullName, address:address, email:email, mobileNumber:mobileNumber}
        });
    }
	
    this.deletePerson = function deletePerson(personId){
    	var parsedId = Number.parseInt(personId);
    	if(Number.isInteger(parsedId) && parsedId > 0){
    		return $http({
    			method: 'DELETE',
    			url: 'persons/'+ parsedId
    		});
    	}
    }
	
    this.updatePerson = function updatePerson(personId, firstName, lastName, fullName, address, email, mobileNumber){
        return $http({
          method: 'PATCH',
          url: 'persons/'+ personId,
          data: {firstName:firstName, lastName:lastName, fullName:fullName, address:address, email:email, mobileNumber:mobileNumber}
        })
    }
	
    this.getAllPersons = function getAllPersons(){
        return $http({
          method: 'GET',
          url: 'persons?sort=firstName,asc'
        });
    }

}]);