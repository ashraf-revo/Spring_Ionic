angular.module("starter").controller("FollowCtrl", function ($scope, User, $state) {
  $scope.users = [];
  $scope.following = [];
  if ($state.current.name == 'following') {
    $scope.follow = "Following";
  } else {
    $scope.follow = "Followers";
  }
  $scope.users = [];
  $scope.following = [];
  $scope.inUsers = function (user) {
    var x = false;
    for (var i = 0; i < $scope.following.length; i++)if (user.id == $scope.following[i].id)x = true;
    return x;
  };

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
    User.following($state.params.id).success(function (users) {
      $scope.following = angular.copy(users);
      if ($state.current.name == 'following')      $scope.users = angular.copy(users);
    });

    User.followers($state.params.id).success(function (users) {
      if ($state.current.name == 'followers')      $scope.users = angular.copy(users);
    });
  };
  $scope.load()
});
