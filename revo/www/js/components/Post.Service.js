'use strict';
angular.module("starter").factory("Post", function ($http, URL) {
  var url = "api/post";
  return {
    posts: function (id) {
      return $http.get(URL + url + "?id=" + id);
    }, postsUser: function (user,id) {
      return $http.get(URL + url + "/"+user+"?id=" + id);
    },
    save: function (post) {
      return $http.post(URL + url, post);
    }
  }
});
