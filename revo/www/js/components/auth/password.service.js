'use strict';

angular.module('starter')
    .factory('Password', function ($resource,URL) {
        return $resource(URL+'api/account/change_password', {}, {
        });
    });

angular.module('starter')
    .factory('PasswordResetInit', function ($resource,URL) {
        return $resource(URL+'api/account/reset_password/init', {}, {
        })
    });

angular.module('starter')
    .factory('PasswordResetFinish', function ($resource,URL) {
        return $resource(URL+'api/account/reset_password/finish', {}, {
        })
    });
