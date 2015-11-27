package rst.vertical.service.catalog.model;

import org.springframework.data.annotation.Id;

public class CatalogItem {

	@Id
	private String articleNumber;
	private String name;
	private String description;
	private double price;
	
	public CatalogItem(String articleNumber, String name, String description, double price) {
		super();
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	protected CatalogItem() {}

	public String getArticleNumber() {
		return articleNumber;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "CatalogItem [articleNumber=" + articleNumber + ", name=" + name
				+ ", description=" + description + ", price=" + price + "]";
	}
	
}
