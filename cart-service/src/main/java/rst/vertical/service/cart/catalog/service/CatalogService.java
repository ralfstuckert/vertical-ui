package rst.vertical.service.cart.catalog.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import rst.vertical.service.cart.catalog.model.CatalogItem;

@Service
public class CatalogService {

	@Value("${service.catalog.url:http://localhost:9000/catalog/api/%s}")
	private String catalogServiceUrl;
	
	private RestTemplate restTemplate;

	private final static ParameterizedTypeReference<List<CatalogItem>> CATALOG_ITEM_LIST_TYPE = new ParameterizedTypeReference<List<CatalogItem>>() {
	};;

	@Autowired
	public CatalogService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<CatalogItem> getItems(String... articleNumbers) {
		if (articleNumbers.length == 0) {
			return Collections.emptyList();
		}
		String url = String.format(catalogServiceUrl, "items");
		UriComponents comp = UriComponentsBuilder.fromHttpUrl(
				url).queryParam("article", articleNumbers).build();
		return restTemplate.exchange(comp.toString(), HttpMethod.GET, null, CATALOG_ITEM_LIST_TYPE).getBody();
	}
}
