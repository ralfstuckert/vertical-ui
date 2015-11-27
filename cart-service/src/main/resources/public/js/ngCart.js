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
		$http.get('/cart/api/cart').then(updateCart, updateCartFailed)
	}

	function addItem(id, quantity) {
		var data = {
			'id' : id,
			'quantity' : quantity
		}
		$http.post('/cart/api/cart/add/'+id, data).then(updateCart, updateCartFailed)
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
		getCart : getCart,
		fetchCart : fetchCart
	}

} ])


.controller(
		'CartController',
		[ '$scope', '$log', 'ngCartService', 'ngDialog', 
				function($scope, $log, ngCartService, ngDialog) {
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
					
							$scope.sum = function() {
								var cart = ngCartService.getCart()
								var sum = 0.0;
								for (var index in cart.items) {
									var item = cart.items[index]
									sum += item.count * item.price;
								}
								return sum;
							}

					$scope.showCart = function() {
						ngDialog.open({
					    template: 'cart/template/showCart.html',
					    controller: ['$scope', 'ngCartService', function($scope, ngCartService) {
					    }]
					  });
					}

					$scope.dynamicPopover = {
							templateUrl: 'cart/template/showCart.html',
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
