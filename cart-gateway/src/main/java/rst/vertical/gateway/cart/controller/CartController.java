package rst.vertical.gateway.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rst.vertical.gateway.cart.extended.model.ExtendedCart;
import rst.vertical.gateway.cart.extended.service.ExtendedCartService;

@RestController
@RequestMapping(value = "api/cart")
public class CartController {

	private ExtendedCartService extendedCartService;

	@Autowired
	public CartController(ExtendedCartService cartService) {
		this.extendedCartService = cartService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ExtendedCart getExtendedCart() {
		return extendedCartService.getCart();
	}

	@RequestMapping(value = "/add/{articleNumber}", method = RequestMethod.POST)
	public ExtendedCart addItem(
			@PathVariable("articleNumber") String articleNumer) {
		return extendedCartService.addItem(articleNumer);
	}

	@RequestMapping(value = "/remove/{articleNumber}", method = RequestMethod.POST)
	public ExtendedCart removeItem(
			@PathVariable("articleNumber") String articleNumer) {
		return extendedCartService.removeItem(articleNumer);
	}

	@RequestMapping(value = "{articleNumber}", method = RequestMethod.DELETE)
	public ExtendedCart removeAll(
			@PathVariable("articleNumber") String articleNumer) {
		return extendedCartService.removeAll(articleNumer);
	}

}
