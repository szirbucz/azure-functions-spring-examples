package eu.wecanconsulting.example.hello_world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;

@ExtendWith(MockitoExtension.class)
class HelloWorldHandlerTest {

	private static final String EXPECTED = "EXPECTED";

	@Spy
	private HelloWorldHandler helloWorldHandler;

	@Mock
	private ExecutionContext executionContext;

	@Mock
	private HttpRequestMessage<Optional<String>> request;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		doReturn(mock(Logger.class)).when(executionContext).getLogger();
		doReturn(EXPECTED).when(helloWorldHandler).handleRequest(Optional.of(EXPECTED), executionContext);
		doReturn(Optional.of(EXPECTED)).when(request).getBody();
		doReturn(new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(HttpStatus.OK))
		.when(request).createResponseBuilder(HttpStatus.OK);
	}

	@Test
	@DisplayName("The response body should contain what the handleRequest returned")
	void testResponseBodyIsSet() {
		final HttpResponseMessage result = helloWorldHandler.run(request , executionContext);
		helloWorldHandler.close();
		assertEquals(EXPECTED, result.getBody());
	}

}
