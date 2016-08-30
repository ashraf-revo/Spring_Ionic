angular.module("starter").config(function ($stateProvider) {
    $stateProvider.state("register", {
        parent: 'site',
        url: '/register',
        data: {
            roles: []
        },
      cache: false,
      views:{
         'content@':{
             templateUrl: 'js/app/Register/register.html',
             controller: 'RegisterCtrl'
         }
        }
    })
});
