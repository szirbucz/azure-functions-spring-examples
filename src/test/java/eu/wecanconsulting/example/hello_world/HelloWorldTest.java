package eu.wecanconsulting.example.hello_world;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Unit test for Function class.
 */

@ExtendWith(MockitoExtension.class)
public class HelloWorldTest {

	private static final String HELLO = "Hello ";
	private static final String HELLO_OSN = HELLO + "OSN";
	private static final String AZURE = "Azure";
	private static final String HELLO_AZURE = HELLO + AZURE;

	/**
	 * Unit test for HttpTriggerJava method.
	 *
	 */
	@InjectMocks
	private HelloWorld helloFunction;

	@Mock
	private HelloService helloService;

	@BeforeEach
	public void setup() {
		doAnswer(invocation -> HELLO + invocation.getArgument(0)).when(helloService).sayHello(anyString());
	}


	@Test
	@DisplayName("Hello <name> should be returned, when name is defined")
	public void testReturnHelloNameWhenNameIsGiven() throws Exception {
		final String ret = helloFunction.apply(Optional.of(AZURE));

		assertEquals(HELLO_AZURE, ret);
	}

	@Test
	@DisplayName("Hello OSN should be returned, when name is not defined")
	public void testReturnHelloSorosWhenNameIsNotGiven() throws Exception {
		final String ret = helloFunction.apply(Optional.empty());

		assertEquals(HELLO_OSN, ret);
	}

}
