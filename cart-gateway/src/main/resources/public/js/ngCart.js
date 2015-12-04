'use strict';

angular.module('ngCart', [])

.config([ function() {

} ])

.run( [ '$rootScope', '$log', function($rootScope, $log) {
} ])

.factory('ngCartService', [ '$rootScope', '$http', function($rootScope, $http) {

	var cart = {
		items : []
	};

	function init() {
		fetchCart();
	}

	function getCart() {
		return cart;
	}

	function fetchCart() {
		$http.get('/api/cart').then(updateCart, updateCartFailed)
	}

	function addItem(id, quantity) {
		var data = {
			'id' : id,
			'quantity' : quantity
		}
		$http.post('/api/cart/add/'+id, data).then(updateCart, updateCartFailed)
	}

	function removeAll(id) {
		$http.delete('/api/cart/'+id).then(updateCart, updateCartFailed)
	}

	function updateCart(result) {
		cart = result.data
		$rootScope.$broadcast('ngCart:change', cart);
	}

	function updateCartFailed(result) {
		$rootScope.$broadcast('ngCart:failed', result);
	}

	return {
		init : init,
		addItem : addItem,
		removeAll : removeAll,
		getCart : getCart,
		fetchCart : fetchCart
	}

} ])


.controller(
		'CartController',
		[ '$scope', '$log', 'ngCartService', 
				function($scope, $log, ngCartService) {
					$log.info("running the cart controller")
					$scope.cart = {
						count : -1
					}
					$scope.$on('ngCart:change', function(event, cart) {
						$scope.cart = cart;
					})
					$scope.$on('ngCart:failed', function(event, result) {
						$scope.error = 'failed to load cart: ' + result.data.error;
					})
					
					$scope.count = function() {
						var cart = ngCartService.getCart()
						var count = 0;
						for (var index in cart.items) {
							var item = cart.items[index]
							count += item.count;
						}
						return count;
					}

					$scope.removeAll = function(articleNumber) {
						ngCartService.removeAll(articleNumber)
					}

					$scope.dynamicPopover = {
							templateUrl: 'cart/template/content.html',
						    title: 'Title'
						  }
					
					
					ngCartService.fetchCart();
				} ])

.directive('ngCart', [ 'ngCartService', function(ngCartService) {
	return {
		restrict : 'E',
		controller : 'CartController',
		scope : {},
		templateUrl : function(element, attrs) {
			if (typeof attrs.templateUrl == 'undefined') {
				return '/cart/template/cart.html';
			} else {
				return attrs.templateUrl;
			}
		},
		link : function(scope, element, attrs) {
		}
	};
} ])
