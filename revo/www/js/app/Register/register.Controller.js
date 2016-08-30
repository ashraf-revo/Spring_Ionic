angular.module("starter").controller("RegisterCtrl", function ($scope, $state, Auth) {
    $scope.user = {};
    $scope.register = function () {
        Auth.createAccount($scope.user).then(function () {
            Auth.login($scope.user).then(function () {
                $state.go('home');
            });
        });
    };
});
