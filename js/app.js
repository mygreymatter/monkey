(function () {
    var app = angular.module('MonkeyApp', ['ngRoute', 'Home', 'Admin', 'Person']);
    var doesPersonExist = false;

    app.config(function ($routeProvider, $locationProvider) {

        $routeProvider.when('/', {
                templateUrl: 'templates/home.html',
                controller: 'HomeController'
            })
            .when('/admin', {
                templateUrl: 'templates/admin.html',
                controller: 'AdminController'
            }).when('/admin/addperson', {
                templateUrl: 'templates/personeditor.html',
                controller: 'PersonEditController'
            })
            .otherwise({
                redirectTo: '/admin'
            });

        $locationProvider.html5Mode(true);
    });

    /**/


})();
