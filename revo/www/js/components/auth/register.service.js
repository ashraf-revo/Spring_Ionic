'use strict';

angular.module('starter')
    .factory('Register', function ($resource,URL) {
        return $resource(URL+'api/register', {}, {
        });
    });


