package rst.vertical.service.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rst.vertical.service.cart.model.Cart;
import rst.vertical.service.cart.model.ExtendedCartItem;
import rst.vertical.service.cart.service.CartService;

@RestController
@RequestMapping(value = "api/cart")
public class CartController {
	
	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping(value = "", method=RequestMethod.GET)
	public Cart<ExtendedCartItem> getExtendedCart() {
		return cartService.getExtendedCart();
	}

	@RequestMapping(value = "/add/{articleNumber}", method=RequestMethod.POST)
	public Cart<ExtendedCartItem> addItem(@PathVariable("articleNumber") String articleNumer) {
		cartService.addItem(articleNumer);
		return cartService.getExtendedCart();
	}

	@RequestMapping(value = "/remove/{articleNumber}", method=RequestMethod.POST)
	public Cart<ExtendedCartItem> removeItem(@PathVariable("articleNumber") String articleNumer) {
		cartService.removeItem(articleNumer);
		return cartService.getExtendedCart();
	}

}
