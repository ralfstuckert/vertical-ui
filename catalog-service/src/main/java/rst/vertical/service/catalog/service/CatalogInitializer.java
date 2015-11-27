package rst.vertical.service.catalog.service;

import java.util.List;

import rst.vertical.service.catalog.model.CatalogItem;

import com.google.common.collect.Lists;

public class CatalogInitializer {

	public static List<CatalogItem> createCatalog() {
		List<CatalogItem> catalog = Lists.newArrayList();
		int articleNumber = 1;//new Random().nextInt(100000);
		catalog.add(new CatalogItem(Integer.toString(articleNumber++), "Nnnnuts", "The chunkiest peanut butter ever, made of non-gound nuts", 2.68));
		catalog.add(new CatalogItem(Integer.toString(articleNumber++), "ChoCoCo", "Devine chocolate with whole coconuts", 1.67));
		catalog.add(new CatalogItem(Integer.toString(articleNumber++), "Glowing Cow", "The number one illuminating milkshake. Available in various flavors", .88));
		catalog.add(new CatalogItem(Integer.toString(articleNumber++), "Hazel Brazil", "Hazelnut flavored brazil nuts. You gotta try this", 3.45));
		catalog.add(new CatalogItem(Integer.toString(articleNumber++), "Creme'o'rama", "This chocolate creme is without any natural ingrediants, quite perfect for allergic subjects", 4.54));
		
		return catalog;
	}
	
}
