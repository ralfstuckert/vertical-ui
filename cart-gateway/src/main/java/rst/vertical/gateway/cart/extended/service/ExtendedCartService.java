package rst.vertical.gateway.cart.extended.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rst.vertical.gateway.cart.catalog.model.CatalogItem;
import rst.vertical.gateway.cart.catalog.service.CatalogService;
import rst.vertical.gateway.cart.extended.model.ExtendedCart;
import rst.vertical.gateway.cart.extended.model.ExtendedCartItem;
import rst.vertical.gateway.cart.model.Cart;
import rst.vertical.gateway.cart.model.CartItem;
import rst.vertical.gateway.cart.service.CartService;

@Service
public class ExtendedCartService {

	private CatalogService catalogService;
	private CartService cartService;

	@Autowired
	public ExtendedCartService(CatalogService catalogService,
			CartService cartService) {
		this.catalogService = catalogService;
		this.cartService = cartService;
	}

	public ExtendedCart getCart() {
		Cart cart = cartService.getCart();
		return createExtendedCart(cart);
	}

	public ExtendedCart addItem(final String articleNumer) {
		Cart cart = cartService.addItem(articleNumer);
		return createExtendedCart(cart);
	}

	public ExtendedCart removeItem(final String articleNumer) {
		Cart cart = cartService.removeItem(articleNumer);
		return createExtendedCart(cart);
	}

	public ExtendedCart removeAll(final String articleNumer) {
		Cart cart = cartService.removeAll(articleNumer);
		return createExtendedCart(cart);
	}

	protected ExtendedCart createExtendedCart(final Cart cart) {
		if (cart.isEmpty()) {
			return new ExtendedCart(Collections.emptyList());
		}
		// get catalog information
		String[] articleNumbers = cart.getItems().stream()
				.map(CartItem::getArticleNumber).toArray(String[]::new);
		Map<String, CatalogItem> catalogItems = catalogService
				.getItems(articleNumbers)
				.stream()
				.collect(
						Collectors.toMap(CatalogItem::getArticleNumber,
								Function.identity()));

		// enrich cart items with catalog data
		List<ExtendedCartItem> extendedItems = cart
				.getItems()
				.stream()
				.map(item -> {
					CatalogItem catalogItem = catalogItems.get(item
							.getArticleNumber());
					return new ExtendedCartItem(item, catalogItem.getName(),
							catalogItem.getPrice());
				}).collect(Collectors.toList());

		return new ExtendedCart(extendedItems);
	}
}