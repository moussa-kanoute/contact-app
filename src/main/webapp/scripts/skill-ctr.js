app.controller('SkillCRUDCtrl', ['$scope','SkillCRUDService', function ($scope,SkillCRUDService) {

	$scope.updateSkill = function () {
		SkillCRUDService.updateSkill($scope.skill.id, $scope.skill.name)
			.then(function success(response){
				$scope.message = 'Skill data updated!';
				$scope.errorMessage = '';
			},
			function error(response){
				$scope.errorMessage = 'Error updating skill!';
				$scope.message = '';
			});
		}
    
    $scope.getSkill = function () {
    	var id = $scope.skill.id;
    	SkillCRUDService.getSkill($scope.skill.id)
        	.then(function success(response){
        		$scope.skill = response.data;
        		$scope.skill.id = id;
        		$scope.message='';
        		$scope.errorMessage = '';
        	},
        	function error (response ){
        		$scope.message = '';
        		if (response.status === 404){
        			$scope.errorMessage = 'Skill not found!';
        		}
        		else {
        			$scope.errorMessage = "Error getting skill!";
        		}
          }
        );
    }
    
    $scope.addSkill = function () {
    	if ($scope.skill != null && $scope.skill.name) {
    		SkillCRUDService.addSkill($scope.skill.name)
              .then (function success(response){
            	  $scope.message = 'Skill added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding skill!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please review skill details!';
            $scope.message = '';
        }
    }
    
    $scope.deleteSkill = function () {
    	SkillCRUDService.deleteSkill($scope.skill.id)
          .then (function success(response){
              $scope.message = 'Skill deleted!';
              $scope.skill = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting skill!';
              $scope.message='';
          })
    }
    
    $scope.getAllSkills = function () {
    	SkillCRUDService.getAllSkills()
          .then(function success(response){
              $scope.skills = response.data._embedded.skills;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting skills!';
          });
    }

}]);

app.service('SkillCRUDService',['$http', function ($http) {
	
    this.getSkill = function getSkill(skillId){
        return $http({
          method: 'GET',
          url: 'skills/'+ skillId
        });
	}
	
    this.addSkill = function addSkill(name){
        return $http({
          method: 'POST',
          url: 'skills',
          data: {name:name}
        });
    }
	
    this.deleteSkill = function deleteSkill(skillId){
        return $http({
          method: 'DELETE',
          url: 'skills/'+ skillId
        })
    }
	
    this.updateSkill = function updateSkill(skillId, name){
        return $http({
          method: 'PATCH',
          url: 'skills/'+ skillId,
          data: {name:name}
        })
    }
	
    this.getAllSkills = function getAllSkills(){
        return $http({
          method: 'GET',
          url: 'skills'
        });
    }

}]);