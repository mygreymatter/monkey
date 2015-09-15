(function () {

    angular.module('Person', ['PersonFactory']).controller('PersonEditController', function ($scope, $location, PersonsFactory) {
        var index = PersonsFactory.persons.getSelected();
        console.log('person editor' + index);

        $scope.person = {}
        if (index > -1)
            $scope.person = PersonsFactory.persons[index];

        $scope.title = index > -1 ? $scope.person.name + " Editor" : 'Person Editor';

        $scope.createPerson = function () {
            if (index == -1) {
                PersonsFactory.persons.push($scope.person);
                doesPersonExist = true;
            } else {
                PersonsFactory.persons[index] = $scope.person;
                doesPersonExist = false;
            }

            $location.path("/admin");
            console.log("After set" + $location.path());
        };

        $scope.$on('$destroy', function () {
            console.log("Destroying the Editor");
            PersonsFactory.persons.setSelected(-1);
        });
    });
})();
