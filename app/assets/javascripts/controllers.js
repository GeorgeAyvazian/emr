var emrControllers = angular.module('emrControllers', []);

emrControllers.controller('AppCtrl', ['$scope', '$http', function ($scope, $http) {
}]);

emrControllers.controller('PatientCtrl', ['$scope', '$resource', '$routeParams', 'apiUrl', function ($scope, $resource, $routeParams, apiUrl) {
    var patientsResource = $resource(apiUrl + '/patients');
    if (window.console) {
        window.console.log('abbazabba: ' + $routeParams.id);
    }
    $scope.patient = {};
    $scope.patients = [];
    $scope.savePatient = function savePatient() {
        var resource = $resource(apiUrl + '/patients/new'), save = resource.save($scope.patient);
        save.$promise.then(function (result) {
            if (window.console) {
                window.console.log(arguments);
                window.console.log(result);
            }
            $scope.patients.push($scope.patient);
        });
    };

    $scope.deletePatient = function deletePatient(id) {
        $resource(apiUrl + '/patients/' + id)['delete']();
    };

    patientsResource.query().$promise.then(function (result) {
        if (window.console) {
            window.console.log(result[0]);
            window.console.log(result[0].length);
            window.console.log(result[0][0]);
        }
        $scope.patients.push(result[0][0]);
    });
}]);