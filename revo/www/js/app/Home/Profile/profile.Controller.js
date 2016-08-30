angular.module("starter").controller("ProfileCtrl", function ($scope, User, Post, $state) {
  $scope.user = {};
  $scope.canLoad = true;
  $scope.posts = [];
  $scope.postId = "";
  User.user($state.params.id).success(function (user) {
    $scope.user = user;
  });
  $scope.load = function () {
    Post.postsUser($state.params.id, $scope.postId).success(function (posts) {
      if (posts.length > 0)$scope.postId = posts[posts.length - 1].id;
      if (posts.length == 0)$scope.canLoad = false;
      Array.prototype.push.apply($scope.posts, posts);
      $scope.$broadcast('scroll.infiniteScrollComplete');
    });
  };
});
