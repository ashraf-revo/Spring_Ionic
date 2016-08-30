'use strict';
angular.module("starter").factory("User", function ($http, URL) {
  var url = "api/user";
  return {
    users: function (id) {
      return $http.get(URL + url + "/all?id=" + id);
    },
    user: function (id) {
      return $http.get(URL + url + "/" + id);
    },
    followers: function (id) {
      return $http.get(URL + url + "/" + id + "/followers");
    },
    following: function (id) {
      return $http.get(URL + url + "/" + id + "/following");
    },
    follow: function (id, state) {
      return $http.get(URL + url + "/follow/" + id + "?state=" + state);
    }
  }
});
