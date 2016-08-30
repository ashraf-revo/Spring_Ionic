'use strict';

angular.module('starter')
    .factory('Account', function Account($resource,URL) {
        return $resource(URL+'api/user', {}, {
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            }
        });
    });
