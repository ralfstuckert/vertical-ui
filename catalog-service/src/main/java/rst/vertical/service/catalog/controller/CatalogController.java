package rst.vertical.service.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rst.vertical.service.catalog.model.CatalogItem;
import rst.vertical.service.catalog.service.CatalogService;

@RestController
@RequestMapping(value = "api")
public class CatalogController {

	private CatalogService catalogService;
	
	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public List<CatalogItem> getAllItems() {
		return catalogService.getAllItems();
	}
	
	@RequestMapping(value = "/items", method=RequestMethod.GET)
	public List<CatalogItem> getItems(@RequestParam(value = "article") String[] articleNumbers) {
		return catalogService.getItems(articleNumbers);
	}
	
	@RequestMapping(value = "/{article}", method=RequestMethod.GET)
	public CatalogItem getItem(@PathVariable(value = "article") final String articleNumber) {
		return catalogService.getItem(articleNumber);
	}
}
