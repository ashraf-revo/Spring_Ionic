angular.module("starter").controller("HomeCtrl", function (UtilService,$ionicLoading, $cordovaLocalNotification, $cordovaCamera, MessageService, $scope, Auth, $state, Post, $ionicModal, $ionicScrollDelegate) {
  $scope.posts = [];
  $scope.canLoad = false;
  $scope.postId = "";
  MessageService.connectIfNot();
  $ionicModal.fromTemplateUrl('js/app/Home/newPost.html', {
    scope: $scope,
    animation: 'slide-in-up'
  }).then(function (modal) {
    $scope.modal = modal;
    $scope.post = {'content': '', 'file': ''}
  });
  $scope.save = function (post) {
    Post.save(post).success(function (p) {
      if (UtilService.checkIn(p,$scope.posts)) {
        $scope.posts.unshift(p);
        $ionicScrollDelegate.scrollTop();
        post = {'content': '', 'file': ''}
      }
    });
  };
  $scope.load = function () {
    Post.posts($scope.postId).success(function (posts) {
      if (posts.length == 0)$scope.canLoad = false;
      else {
        $scope.postId = posts[posts.length - 1].id;
        $scope.canLoad = true;
        Array.prototype.push.apply($scope.posts, posts);
        $scope.$broadcast('scroll.infiniteScrollComplete');
      }
    });
  };
  MessageService.subscribe().then(null, null, function (post) {
    if (UtilService.checkIn(post,$scope.posts))
      $scope.posts.unshift(post)
  });

  $scope.newPost = function () {
    $scope.modal.show();
  };
  $scope.selectImage = function (post) {
    document.addEventListener("deviceready", function () {
      var options = {
        destinationType: Camera.DestinationType.DATA_URL,
        sourceType: Camera.PictureSourceType.CAMERA,
        encodingType: Camera.EncodingType.JPEG
      };

      $cordovaCamera.getPicture(options).then(function (imageData) {
        post.file = imageData;
      }, function (err) {

      });

    }, false);
  };
  $scope.load();
});
