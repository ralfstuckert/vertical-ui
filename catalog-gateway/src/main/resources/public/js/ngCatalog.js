'use strict';

angular.module('ngCatalog', [ 'ngResource' ])

.config([ function() {
} ])

.factory('ngCatalogService', [ '$http', function($http) {

	function getCatalog() {
		return $http.get('/api/catalog/all');
	}
	
	return {
		getCatalog : getCatalog
	};

} ])

.controller('CatalogController', [ '$scope', '$log', 'ngCatalogService', 'ngCartService', function($scope, $log, ngCatalogService, ngCartService) {
	$scope.ngCartService = ngCartService;
	ngCatalogService.getCatalog().then(function(result) {
        $scope.data = result.data;
    }, function(result) {
        $scope.error = 'failed to load catalog: ' + result.data.error;
    })
} ])

.directive('ngCatalog', [ 'ngCatalogService', function(ngCatalogService) {
	return {
		restrict : 'E',
		controller : 'CatalogController',
		scope : {},
		templateUrl : function(element, attrs) {
			if (typeof attrs.templateUrl == 'undefined') {
				return '/catalog/template/catalog.html';
			} else {
				return attrs.templateUrl;
			}
		},
		link : function(scope, element, attrs) {
		}
	};
} ])