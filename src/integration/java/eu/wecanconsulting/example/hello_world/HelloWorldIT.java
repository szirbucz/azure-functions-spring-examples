package eu.wecanconsulting.example.hello_world;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import eu.wecanconsulting.example.Config;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Config.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class HelloWorldIT {
	private static final String KAREN = "Karen";
	private static final String HELLO_OSN = "Hello OSN";
	private static final String HELLO_KAREN = "Hello " + KAREN;
	private static final String PATH = "/HelloWorld";
	@Autowired
	private WebTestClient client;

	@Test
	@DisplayName("Hello <name> should be returned, when name is defined")
	public void testGreetsBodyContentWhenItIsGiven() throws Exception {
		client.post()
		.uri(PATH)
		.body(Mono.just(KAREN), String.class)
		.exchange()
		.expectBody(String.class)
		.isEqualTo(HELLO_KAREN);

	}

	//	@Test
	//	@DisplayName("Hello OSN should be returned, when name is not defined")
	//	public void testGreetsSorosWhenBodyContentIsNotGiven() throws Exception {
	//		client.post()
	//		.uri(PATH)
	//		.exchange()
	//		.expectBody(String.class)
	//		.isEqualTo(HELLO_OSN);
	//	}
}
