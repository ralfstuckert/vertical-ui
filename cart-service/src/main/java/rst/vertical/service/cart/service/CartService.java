package rst.vertical.service.cart.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rst.vertical.service.cart.catalog.model.CatalogItem;
import rst.vertical.service.cart.catalog.service.CatalogService;
import rst.vertical.service.cart.model.CartImpl;
import rst.vertical.service.cart.model.CartItem;
import rst.vertical.service.cart.model.ExtendedCart;
import rst.vertical.service.cart.model.ExtendedCartItem;

@Service
public class CartService {

	private CatalogService catalogService;
	private CartImpl cart = new CartImpl();

	@Autowired
	public CartService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public CartImpl getCart() {
		return cart;
	}

	public ExtendedCart getExtendedCart() {
		if (cart.isEmpty()) {
			return new ExtendedCart(Collections.emptyList());
		}
		// get catalog information
		String[] articleNumbers = cart.getItems().stream()
				.map(CartItem::getArticleNumber)
				.toArray(String[]::new);
		Map<String, CatalogItem> catalogItems = catalogService.getItems(articleNumbers)
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

	public CartImpl addItem(final String articleNumer) {
		cart.addItem(articleNumer);
		return cart;
	}

	public CartImpl removeItem(final String articleNumer) {
		cart.removeItem(articleNumer);
		return cart;
	}
}