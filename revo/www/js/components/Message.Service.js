angular.module("starter").factory("MessageService", function ($cordovaLocalNotification, URL, $q, $timeout, localStorageService) {
  var PostListener = $q.defer();
  var stomp = null;
  var connected = false;
  var reconnect = function () {
    $timeout(function () {
      initialize();
    }, 30000);
  };
  var view = function (post) {
    $cordovaLocalNotification.schedule({
      id: 1,
      title: 'Revo',
      text: 'See what '+post.user.username+' Share',
      data: {'id':post.id}
    });

  };

  var startListener = function () {
    connected = true;
    stomp.subscribe('/user/topic/post', function (data) {
      var progress = JSON.parse(data.body);
      PostListener.notify(progress);
      view(progress);
    });
  };

  var initialize = function () {
    var token2 = localStorageService.get('token')['access_token'];
    stomp = Stomp.over(new SockJS(URL + 'hello?access_token=' + token2));
    stomp.connect({Authorization: 'Bearer ' + token2}, startListener,reconnect);

  };
  return {
    subscribe: function () {
      return PostListener.promise
    }, connectIfNot: function () {
      if (!connected)initialize();
    }, disc: function () {
      if (connected) {
        stomp.disconnect();
        connected = false;
      }
    }
  };
});
