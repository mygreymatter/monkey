(function () {
    var app = angular.module('MonkeyApp', ['ngRoute', 'ui.bootstrap']);
    var godhouses = [{
        'religion': 'Hindu',
        'area': 'Jayanagar',
        'name': 'Ganesha Temple'
        }, {
        'religion': 'Christian',
        'area': 'Jayanagar',
        'name': 'Catherine Church'
        }];
    app.config(function ($routeProvider, $locationProvider) {

        $routeProvider.when('/', {
            templateUrl: 'templates/home.html',
            controller: 'HeadController'
        }).when('/admin', {
            templateUrl: 'templates/admin.html',
            controller: 'AdminController'
        }).when('/admin/addgodhouse', {
            templateUrl: 'templates/godhouse-editor.html',
            controller: 'GodHouseEditController'
        }).otherwise({
            redirectTo: '/admin'
        });

        $locationProvider.html5Mode(true);


    });

    app.controller('HeadController', function ($scope) {
        $scope.godhouses = godhouses;
    });

    app.controller('AdminController', function ($scope) {
        $scope.godhouses = godhouses;

    });

    app.controller('GodHouseEditController', function ($scope, $location) {
        console.log('editor');
        $scope.title = 'GodHouse Editor';
        $scope.godhouse = {};
        console.log("outside the function: " + $location.path());
        $scope.createGodHouse = function () {
            console.log("New Godhouse: " + $scope.godhouse);
            godhouses.push($scope.godhouse);
            console.log("New Godhouse: " + godhouses.length);
            console.log($location.path());
            $location.path("/admin");
            console.log("After set" + $location.path());
        };
    });


})();
