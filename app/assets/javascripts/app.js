'use strict';

var app = angular.module("app", ["ngRoute", "ngResource", "emrControllers"])
    .constant('apiUrl', 'http://localhost:9000/api')
    .config(["$routeProvider", function ($routeProvider) {
        return $routeProvider
            .when("/", {
                templateUrl: "/views/index",
                controller: "AppCtrl"
            })
            .when("/patients", {
                templateUrl: "/views/patients",
                controller: "PatientCtrl"
            })
            .otherwise({
                redirectTo: "/"
            });
    }
    ])
    .config(['$resourceProvider', function ($resourceProvider) {

    }])
    .config(["$locationProvider", function ($locationProvider) {
        return $locationProvider.html5Mode(true).hashPrefix("!");
    }
    ]);