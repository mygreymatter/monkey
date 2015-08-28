(function () {
    angular.module('Home', ['PersonFactory']).controller('HomeController', function ($scope, PersonsFactory) {
        $scope.persons = PersonsFactory.persons;
    });

})();
