angular.module("starter").factory("UtilService", function () {
  return {
    checkIn: function (key, list) {
      var bool = true;
      for (var i = 0; i < list.length; i++)if (key.id == list[i].id) {
        bool = false;
        break;
      }
      return bool;
    }
  };
});
