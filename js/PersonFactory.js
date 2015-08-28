(function () {
    angular.module('PersonFactory', []).factory('PersonsFactory', function () {
        var persons = [{
            'area': 'Jayanagar',
            'name': 'Ganesha'
        }, {
            'area': 'Jayanagar',
            'name': 'Catherine'
        }];

        var selected = -1;
        var doesPersonExist = false;

        persons.setSelected = function (index) {
            selected = index;
        }

        persons.getSelected = function () {
            return selected;
        }

        persons.setPersonExisting = function (exist) {
            doesPersonExist = exist;
        }

        persons.getPersonExisting = function () {
            return doesPersonExist;
        }

        return {
            persons
        };
    });
})();
