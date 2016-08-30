'use strict';

angular.module('starter')
    .directive('hasAnyAuthority', ['Principal', function (Principal) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var setVisible = function () {
                        element.removeClass('hidden');
                    },
                    setHidden = function () {
                        element.addClass('hidden');
                    },
                    defineVisibility = function (reset) {
                        var result;
                        if (reset) {
                            setVisible();
                        }

                        result = Principal.hasAnyAuthority(roles);
                        if (result) {
                            setVisible();
                        } else {
                            setHidden();
                        }
                    },
                    roles = attrs.hasAnyAuthority.replace(/\s+/g, '').split(',');

                if (roles.length > 0) {
                    defineVisibility(true);

                    scope.$watch(function(scope) {
                        return Principal.isAuthenticated();
                    }, function(newValue) {
                        defineVisibility(true);
                    });
                }
            }
        };
    }])
    .directive('hasAuthority', ['Principal', function (Principal) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var setVisible = function () {
                        element.removeClass('hidden');
                    },
                    setHidden = function () {
                        element.addClass('hidden');
                    },
                    defineVisibility = function (reset) {

                        if (reset) {
                            setVisible();
                        }

                        Principal.hasAuthority(authority)
                            .then(function (result) {
                                if (result) {
                                    setVisible();
                                } else {
                                    setHidden();
                                }
                            });
                    },
                    authority = attrs.hasAuthority.replace(/\s+/g, '');

                if (authority.length > 0) {
                    defineVisibility(true);

                    scope.$watch(function(scope) {
                        return Principal.isAuthenticated();
                    }, function(newValue) {
                        defineVisibility(true);
                    });
                }
            }
        };
    }]);
