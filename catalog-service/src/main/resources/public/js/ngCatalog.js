'use strict';

angular.module('ngCatalog', [ 'ngResource' ])

.config([ function() {

} ])

.service('ngCatalogRest', [ '$http', function($http) {

	this.getCatalog = function() {
		return $http.get('/catalog/api/all');
	}
} ])

.controller('CatalogController', [ '$scope', 'ngCatalogRest', function($scope, ngCatalogRest) {
} ])

.directive('ngCatalog', [ 'ngCatalogRest', function(ngCatalogRest) {
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
			ngCatalogRest.getCatalog().then(function(result) {
	             scope.data = result.data;
	         }, function() {
	             scope.error = "failed to load catalog from backend";
	         })
		}
	};
} ])