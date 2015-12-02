package rst.vertical.gateway.catalog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api")
public class CatalogProxyController {

	@Value("${service.catalog.host:localhost}")
	private String catalogHost;

	@Value("${service.catalog.port:9001}")
	private int catalogPort;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public void proxy(HttpMethod method, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		URI uri = new URI("http", null, catalogHost, catalogPort,
				request.getRequestURI(), request.getQueryString(), null);
		restTemplate.execute(uri, method, new CopyRequestCallback(request),
				new CopyResponseExctractor(response));
	}

	private static class CopyResponseExctractor implements
			ResponseExtractor<Void> {

		private HttpServletResponse response;

		public CopyResponseExctractor(HttpServletResponse response) {
			super();
			this.response = response;
		}

		public Void extractData(ClientHttpResponse clientResponse)
				throws IOException {
			copyHeaders(clientResponse);
			try (InputStream in = clientResponse.getBody();
					OutputStream out = response.getOutputStream()) {
				IOUtils.copy(in, out);
			}
			return null;
		}

		protected void copyHeaders(ClientHttpResponse clientResponse) {
			HttpHeaders headers = clientResponse.getHeaders();
			for (String name : headers.keySet()) {
				List<String> values = headers.get(name);
				for (String value : values) {
					response.addHeader(name, value);
				}
			}
		}
	}

	private static class CopyRequestCallback implements RequestCallback {

		private HttpServletRequest request;

		public CopyRequestCallback(HttpServletRequest request) {
			this.request = request;
		}

		public void doWithRequest(ClientHttpRequest clientRequest)
				throws IOException {
			copyHeaders(clientRequest);
			try (InputStream in = request.getInputStream();
					OutputStream out = clientRequest.getBody()) {
				IOUtils.copy(in, out);
			}
		}

		protected void copyHeaders(ClientHttpRequest clientRequest) {
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				Enumeration<String> values = request.getHeaders(name);
				while (values.hasMoreElements()) {
					clientRequest.getHeaders().add(name, values.nextElement());
				}
			}
		}

	}
}
