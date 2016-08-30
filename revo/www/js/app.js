angular.module('starter', ['ionic', 'ngCordova', 'LocalStorageModule', 'ngResource', 'ngCacheBuster', 'ion-floating-menu'])
  .constant('URL', 'http://soklin-exhortative-impetrator.cfapps.io/')
  /*
   .constant('URL', 'http://localhost:8080/')
   */
  .run(function ($cordovaToast, $rootScope, $location, $window, $http, $state, Auth, Principal, $ionicPlatform) {
    $ionicPlatform.ready(function () {
      if (window.cordova && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        cordova.plugins.Keyboard.disableScroll(true);
      }
      if (window.StatusBar) {
        StatusBar.styleDefault();
      }
    });
    $rootScope.$on('$cordovaLocalNotification:click',
      function (event, notification) {
        var postId = JSON.parse(JSON.parse(JSON.stringify(notification)).data).id;
        // $cordovaToast.show(postId , 'long', 'center')

      });
    $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
      $rootScope.toState = toState;
      $rootScope.toStateParams = toStateParams;
      if (Principal.isIdentityResolved()) {
        Auth.authorize();
      }
    });
    $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
      $rootScope.previousStateName = fromState.name;
      $rootScope.previousStateParams = fromParams;
    });
  })
  .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, httpRequestInterceptorCacheBusterProvider) {

    httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
    $urlRouterProvider.otherwise('/login');
    // $urlRouterProvider.otherwise('/');
    $stateProvider.state('site', {
      'abstract': true,
      resolve: {
        authorize: ['Auth',
          function (Auth) {
            return Auth.authorize(true);
          }
        ]

      }
    });
    $httpProvider.interceptors.push('authExpiredInterceptor');
    $httpProvider.interceptors.push('authInterceptor');
  });
