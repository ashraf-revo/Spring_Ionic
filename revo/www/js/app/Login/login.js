angular.module("starter").config(function ($stateProvider) {
  $stateProvider.state("login", {
    parent: 'site',
    url: '/login',
    data: {
      roles: []
    },
    cache: false,
    views: {
      'content@': {
        templateUrl: 'js/app/Login/login.html',
        controller: 'LoginCtrl'
      }
    }
  })
});
