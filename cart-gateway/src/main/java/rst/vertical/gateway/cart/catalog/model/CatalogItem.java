package rst.vertical.gateway.cart.catalog.model;


public class CatalogItem {

	private String articleNumber;
	private String name;
	private double price;
	
	public CatalogItem(String articleNumber, String name, double price) {
		super();
		this.articleNumber = articleNumber;
		this.name = name;
		this.price = price;
	}
	
	protected CatalogItem() {}

	public String getArticleNumber() {
		return articleNumber;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
}
