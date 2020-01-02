app.controller('ContactCtrl', ['$scope', '$interval', 'PersonCRUDService', function ($scope, $interval, PersonCRUDService) {

    $scope.getAllPersons = function () {
        PersonCRUDService.getAllPersons()
          .then(function success(response){
              $scope.persons = response.data._embedded.persons;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting persons!';
          });
    }

    $scope.deletePerson = function (personId) {
    	PersonCRUDService.deletePerson(personId)
          .then (function success(response){
        	  $scope.initData();
              $scope.message = 'Person deleted!';
              $scope.person = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting person!';
              $scope.message='';
          })
    }

    $scope.searchPerson = function () {
    	// Declare variables
    	var input, filter, table, tr, td, i, txtValue;
    	
    	input = document.getElementById("inputName");
    	filter = input.value.toUpperCase();
    	table = document.getElementById("personTable");
    	tr = table.getElementsByTagName("tr");

    	// Loop through all table rows, and hide those who don't match the search query
    	for (i = 0; i < tr.length; i++) {
    		td = tr[i].getElementsByTagName("td")[1];
    		if (td) {
    			txtValue = td.textContent || td.innerText;
    			if (txtValue.toUpperCase().indexOf(filter) > -1) {
    				tr[i].style.display = "";
    			} else {
    				tr[i].style.display = "none";
    			}
    		}
    	};
    }

    $scope.setClickedRow = function(person){
    	if ($scope.selectedId == person.id){
    		$scope.selectedId = null;
    	} else{
        	$scope.selectedId = person.id;
    	}
    	
    	if($scope.selectedId == null){
    		$('#addAction').show();
    		$('#deleteAction').hide();
    	} else {
    		$('#addAction').hide();
    		$('#deleteAction').show();
    	}
    }

    $interval(function () {
    	$scope.getAllPersons();
    }, 60000);

    $scope.initData = function () {
    	$scope.selectedId = null;
    	$('#deleteAction').hide();
		$('#addAction').show();
        $scope.getAllPersons();
    }

    $scope.initData();
}]);
