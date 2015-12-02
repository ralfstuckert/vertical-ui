package rst.vertical.service.cart.service;

import org.springframework.stereotype.Service;

import rst.vertical.service.cart.model.Cart;

@Service
public class CartService {

	private Cart cart = new Cart();

	public Cart getCart() {
		return cart;
	}

	public Cart addItem(final String articleNumer) {
		cart.addItem(articleNumer);
		return cart;
	}

	public Cart removeItem(final String articleNumer) {
		cart.removeItem(articleNumer);
		return cart;
	}

	public Cart removeAll(final String articleNumer) {
		cart.removeAll(articleNumer);
		return cart;
	}
}