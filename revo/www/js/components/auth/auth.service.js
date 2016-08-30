'use strict';

angular.module('starter')
    .factory('Auth', function Auth($ionicPlatform,$rootScope, $state, $q, Principal, AuthServerProvider, Account, Register, Activate, Password, PasswordResetInit, PasswordResetFinish) {
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();

                AuthServerProvider.login(credentials).then(function (data) {
                    // retrieve the logged account information
                    Principal.identity(true).then(function(account) {
                        deferred.resolve(data);
                    });
                    return cb();
                }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },

            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
                // Reset state memory
                $rootScope.previousStateName = undefined;
                $rootScope.previousStateNameParams = undefined;
            },

            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                        // an authenticated user can't access to login and register pages
                        if (isAuthenticated  && ($rootScope.toState.name === 'login' || $rootScope.toState.name === 'register'|| $rootScope.toState.name === 'password')) {
                            $state.go('home');
                        }

                        if ($rootScope.toState.data.roles && $rootScope.toState.data.roles.length > 0 && !Principal.hasAnyAuthority($rootScope.toState.data.roles)) {
                            if (isAuthenticated) {
                                $state.go('accessdenied');
                            }
                            else {
                                // user is not authenticated. stow the state they wanted before you
                                // send them to the signin state, so you can return them when you're done
                                $rootScope.previousStateName = $rootScope.toState;
                                $rootScope.previousStateNameParams = $rootScope.toStateParams;

                                // now, send them to the signin state so they can log in
                                $state.go('login');
                            }
                        }

                      $ionicPlatform.ready(function() {
                        setTimeout(function() {
                          navigator.splashscreen.hide();
                        }, 300);
                      });

                    }).$promise;
            },
            createAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Register.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            },

            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Account.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            activateAccount: function (key, callback) {
                var cb = callback || angular.noop;

                return Activate.get(key,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            changePassword: function (newPassword, callback) {
                var cb = callback || angular.noop;

                return Password.save(newPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordInit: function (mail, callback) {
                var cb = callback || angular.noop;

                return PasswordResetInit.save(mail, function() {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordFinish: function(keyAndPassword, callback) {
                var cb = callback || angular.noop;

                return PasswordResetFinish.save(keyAndPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            }
        };
    });
