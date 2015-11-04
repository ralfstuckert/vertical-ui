package rst.vertical.service.catalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import rst.vertical.service.catalog.model.CatalogItem;

import com.google.common.collect.Lists;

@Service
public class CatalogService {
	
	private List<CatalogItem> items;
	
	public CatalogService() {
		this.items = CatalogInitializer.createCatalog();
	}

	public List<CatalogItem> getAllItems() {
		return items;
	}
	
	public List<CatalogItem> getItems(String... articleNumbers) {
		List<String> numbers = Lists.newArrayList(articleNumbers);
		return items.stream().filter(item -> numbers.contains(item.getArticleNumber())).collect(Collectors.toList());
	}
	
	public CatalogItem getItem(String articleNumber) {
		return items.stream().filter(item -> item.getArticleNumber().equals(articleNumber)).findAny().orElse(null);
	}
}
