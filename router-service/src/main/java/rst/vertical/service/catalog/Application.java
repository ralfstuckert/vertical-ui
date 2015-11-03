
package rst.vertical.service.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableZuulProxy
public class Application {

	public static void main(final String[] args) throws Exception {
		new SpringApplication(Application.class).run();
	}

}
