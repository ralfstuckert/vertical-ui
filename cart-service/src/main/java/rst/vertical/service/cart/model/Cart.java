package rst.vertical.service.cart.model;

import java.util.List;

public interface Cart <T extends CartItem> {

	int getCount();
	
	default public boolean isEmpty() {
		return false;
	}

	List<T> getItems();

}
