(function () {
    angular.module('Admin', ['PersonFactory']).controller('AdminController', function ($scope, $timeout, $location, PersonsFactory) {
        $scope.persons = PersonsFactory.persons;
        $scope.doesPersonExist = PersonsFactory.persons.getPersonExisting();
        $scope.message = 'Person Created';
        $scope.class = 'alert-success';

        if ($scope.doesPersonExist) {
            $timeout(function () {
                PersonsFactory.persons.setPersonExisting(false)
                $scope.doesPersonExist = false;
                $scope.$apply();
            }, 2000);
        }

        $scope.showPersonDetails = function (index) {
            console.log("Index: " + index);
            $location.path("/admin/addperson", false);
            $scope.person = PersonsFactory.persons[index];
            PersonsFactory.persons.setSelected(index);
        };

    });


})();
