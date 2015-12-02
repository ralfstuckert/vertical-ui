package rst.vertical.gateway.cart.model;

public class CartItem {

	private String articleNumber;
	private int count;

	public CartItem(final String articleNumber) {
		this.articleNumber = articleNumber;
	}
	
	/**
	 * For Jackson only
	 */
	protected CartItem() {}
	
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increaseCount(int delta) {
		count += delta;
	}

	public void decreaseCount(int delta) {
		count -= delta;
	}

	@Override
	public String toString() {
		return "CartItem [articleNumber=" + articleNumber + ", count=" + count
				+ "]";
	}
	
	
}
