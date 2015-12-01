var myApp = angular.module('myApp',['ngCatalog', 'ngCart', 'ui.bootstrap']);

myApp.controller ('myCtrl', ['$scope', '$http', function($scope, $http) {
    
}]);

// avoid template caching
myApp.run(function($rootScope, $templateCache) {
    $rootScope.$on('$routeChangeStart', function(event, next, current) {
        if (typeof(current) !== 'undefined'){
            $templateCache.remove(current.templateUrl);
        }
    });
});