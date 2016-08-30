angular.module("starter").controller("LoginCtrl", function ($scope, $state, Auth) {
  $scope.user = {};
  $scope.login = function () {
    Auth.login($scope.user).then(function () {
      $scope.authenticationError = false;
      $state.go('home');
    }).catch(function () {
      $scope.authenticationError = true;
    });
  };
});
