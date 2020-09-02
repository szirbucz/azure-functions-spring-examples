package eu.wecanconsulting.example.hello_world;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;


public class HelloWorldHandler extends AzureSpringBootRequestHandler<Optional<String>, String> {

	@FunctionName("HelloWorld")
	public HttpResponseMessage run(
			@HttpTrigger(
					name = "req",
					methods = {HttpMethod.GET, HttpMethod.POST},
					authLevel = AuthorizationLevel.ANONYMOUS) final
			HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {
		context.getLogger().info("HelloWorld function has been called.");

		return request.createResponseBuilder(HttpStatus.OK)
				.body(handleRequest(request.getBody(), context))
				.build();
	}

}
