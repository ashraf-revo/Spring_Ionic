'use strict';

angular.module('starter')
    .factory('Activate', function ($resource,URL) {
        return $resource(URL+'api/activate', {}, {
            'get': { method: 'GET', params: {}, isArray: false}
        });
    });


