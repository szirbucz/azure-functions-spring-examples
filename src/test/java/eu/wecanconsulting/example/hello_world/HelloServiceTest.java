package eu.wecanconsulting.example.hello_world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {

	private static final String TOMI = "Tomi";
	private static final String HELLO_TOMI = "Hello " + TOMI;

	@InjectMocks
	private HelloService helloService;

	@Test
	@DisplayName("Hello <name> should be returned")
	void testConcatenatesHelloWithName() {
		final String actual = helloService.sayHello(TOMI);
		assertEquals(HELLO_TOMI, actual);
	}

}
