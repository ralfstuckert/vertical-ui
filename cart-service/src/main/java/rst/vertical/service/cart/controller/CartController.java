package rst.vertical.service.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rst.vertical.service.cart.model.Cart;
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
	public Cart getExtendedCart() {
		return cartService.getCart();
	}

	@RequestMapping(value = "/add/{articleNumber}", method=RequestMethod.POST)
	public Cart addItem(@PathVariable("articleNumber") String articleNumer) {
		return cartService.addItem(articleNumer);
	}

	@RequestMapping(value = "/remove/{articleNumber}", method=RequestMethod.POST)
	public Cart removeItem(@PathVariable("articleNumber") String articleNumer) {
		return cartService.removeItem(articleNumer);
	}

	@RequestMapping(value = "{articleNumber}", method=RequestMethod.DELETE)
	public Cart removeAll(@PathVariable("articleNumber") String articleNumer) {
		return cartService.removeAll(articleNumer);
	}

}
