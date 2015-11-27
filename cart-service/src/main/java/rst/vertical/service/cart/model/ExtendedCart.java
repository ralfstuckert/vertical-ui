package rst.vertical.service.cart.model;

import java.util.ArrayList;
import java.util.List;

public class ExtendedCart implements Cart<ExtendedCartItem> {

	private List<ExtendedCartItem> items = new ArrayList<ExtendedCartItem>();

	public ExtendedCart(List<ExtendedCartItem> items) {
		this.items = items;
	}

	public int getCount() {
		return items.size();
	}

	public List<ExtendedCartItem> getItems() {
		return items;
	}

}
