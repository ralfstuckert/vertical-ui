package rst.vertical.service.cart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartImpl implements Cart<CartItem> {

	private List<CartItem> items = new ArrayList<CartItem>();

	public int getCount() {
		return items.size();
	}
	
	public List<CartItem> getItems() {
		return items;
	}

	public void addItem(final String articleNumber) {
		CartItem item = getExistingItem(articleNumber);
		if (item == null) {
			item = new CartItem(articleNumber);
			items.add(item);
		}
		item.increaseCount(1);
	}

	public void removeItem(final String articleNumber) {
		CartItem item = getExistingItem(articleNumber);
		if (item != null) {
			item.decreaseCount(1);
		}
	}
	
	private CartItem getExistingItem(final String articleNumber) {
		return items.stream().filter(current -> Objects.equals(current.getArticleNumber(),articleNumber)).findFirst().orElse(null);
	}

}
