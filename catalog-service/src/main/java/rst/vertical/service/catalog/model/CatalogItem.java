package rst.vertical.service.catalog.model;

import org.springframework.data.annotation.Id;

public class CatalogItem {

	@Id
	private String articleNumber;
	private String name;
	private String description;
	
	public CatalogItem(String articleNumber, String name, String description) {
		super();
		this.articleNumber = articleNumber;
		this.name = name;
		this.description = description;
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
	
	
}
