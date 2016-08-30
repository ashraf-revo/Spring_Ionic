angular.module("starter").controller("UsersCtrl", function ($scope, User, Principal) {
  $scope.canLoad = true;
  $scope.userId = "";
  $scope.users = [];
  $scope.following = [];
  $scope.inUsers = function (user) {
    var x = false;
    for (var i = 0; i < $scope.following.length; i++)if (user.id == $scope.following[i].id)x = true;
    return x;
  };
  Principal.identity().then(function (user) {
    User.following(user.id).success(function (users) {
      $scope.following = users;
    });
  });
  $scope.follow = function (user, state) {
    User.follow(user.id, state).success(function () {
      if (state) {
        $scope.following.push(user)

      } else {
        for (var i = 0; i < $scope.following.length; i++) {
          if (user.id == $scope.following[i].id) {
            $scope.following.splice(i, 1);
          }
        }
      }
    });
  };
  $scope.load = function () {
    User.users($scope.userId).success(function (users) {
      if (users.length > 0)$scope.userId = users[users.length - 1].id;
      if (users.length == 0)$scope.canLoad = false;
      Array.prototype.push.apply($scope.users, users);
      $scope.$broadcast('scroll.infiniteScrollComplete');
    });
  };
});
