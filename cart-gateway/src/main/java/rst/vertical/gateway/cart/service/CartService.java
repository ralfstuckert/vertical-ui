package rst.vertical.gateway.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rst.vertical.gateway.cart.model.Cart;

@Service
public class CartService {

	@Value("${service.cart.url:http://localhost:9002/api/cart/%s}")
	private String cartServiceUrl;
	
	private RestTemplate restTemplate;

	@Autowired
	public CartService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public Cart getCart() {
		String url = String.format(cartServiceUrl, "");
		return restTemplate.getForObject(url, Cart.class);
	}

	public Cart addItem(final String articleNumber) {
		String url = String.format(cartServiceUrl, "add/{articleNumber}");
		String json = String.format("{'id':'%s', 'quantity':'1'", articleNumber);
		return restTemplate.postForObject(url, json, Cart.class, articleNumber);
	}

	public Cart removeItem(final String articleNumber) {
		String url = String.format(cartServiceUrl, "remove/{articleNumber}");
		String json = String.format("{'id':'%s', 'quantity':'1'", articleNumber);
		return restTemplate.postForObject(url, json, Cart.class, articleNumber);
	}

	public Cart removeAll(final String articleNumber) {
		String url = String.format(cartServiceUrl, "/{articleNumber}");
		return restTemplate.exchange(url, HttpMethod.DELETE, null, Cart.class, articleNumber).getBody();
	}
}