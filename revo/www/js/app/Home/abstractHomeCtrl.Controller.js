angular.module("starter").controller("AbstractHomeCtrl", function ($scope, Principal, Auth,$state) {
  $scope.user = {};
  Principal.identity().then(function (user) {
    $scope.user = user;
  });
  $scope.logout = function () {
    Auth.logout();
    $state.go('login');
  };
});
