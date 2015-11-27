package rst.vertical.service.cart.model;

public class ExtendedCartItem extends CartItem {

	private String name;
	private double price;

	public ExtendedCartItem(final CartItem original, final String name, final double price) {
		super(original.getArticleNumber());
		setCount(original.getCount());
		setName(name);
		setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
