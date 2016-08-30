angular.module("starter").config(function ($stateProvider) {
  $stateProvider.state("abstractHome", {
    parent: 'site',
    url: '/',
    abstract: true,
    data: {
      roles: ['ROLE_USER']
    },
    cache: false,
    views: {
      'content@': {
        templateUrl: 'js/app/Home/abstractHome.html',
        controller:'AbstractHomeCtrl'
      }
    }
  })
    .state("home", {
      parent: 'abstractHome',
      url: '',
      cache: false,
      views: {
        'Content@abstractHome': {
          templateUrl: 'js/app/Home/home.html',
          controller: 'HomeCtrl'
        }
      }
    })
    .state("users", {
      parent: 'abstractHome',
      url: 'users',
      cache: false,
      views: {
        'Content@abstractHome': {
          templateUrl: 'js/app/Home/Users/users.html',
          controller: 'UsersCtrl'
        }
      }
    })
    .state("profile", {
      parent: 'abstractHome',
      url: 'profile/:id',
      cache: false,
      views: {
        'Content@abstractHome': {
          templateUrl: 'js/app/Home/Profile/profile.html',
          controller: 'ProfileCtrl'
        }
      }
    })
    .state("following", {
      parent: 'abstractHome',
      url: 'profile/:id/following',
      cache: false,
      views: {
        'Content@abstractHome': {
          templateUrl: 'js/app/Home/Users/Follow/users.html',
          controller: 'FollowCtrl'
        }
      }
    })
    .state("followers", {
      parent: 'abstractHome',
      url: 'profile/:id/followers',
      cache: false,
      views: {
        'Content@abstractHome': {
          templateUrl: 'js/app/Home/Users/Follow/users.html',
          controller: 'FollowCtrl'
        }
      }
    })
});
