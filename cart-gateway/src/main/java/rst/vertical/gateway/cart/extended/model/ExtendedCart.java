package rst.vertical.gateway.cart.extended.model;

import java.util.ArrayList;
import java.util.List;

public class ExtendedCart  {

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
