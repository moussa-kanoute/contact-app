var app = angular.module('contact-app',["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : "contact.html",
		controller : "ContactCtrl"
	})
	.when("/person/:personId", {
		templateUrl : "person.html",
		controller : "PersonCRUDCtrl"
	});
});

app.filter('int', function() {
    return function(input) {
      return parseInt(input);
    }
});