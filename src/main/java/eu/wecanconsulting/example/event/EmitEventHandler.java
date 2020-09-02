package eu.wecanconsulting.example.event;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.EventHubOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class EmitEventHandler extends AzureSpringBootRequestHandler<String, String> {
	@FunctionName("EmitEvent")
	public HttpResponseMessage todoToTaskDBTrigger(
			@HttpTrigger(
					name = "emitEvent",
					authLevel = AuthorizationLevel.ANONYMOUS,
					methods = {HttpMethod.GET, HttpMethod.POST})
			final HttpRequestMessage<String> request,
			@EventHubOutput(
					name="publishEvent",
					connection = "EventHubConnection",
					eventHubName = "TodoEvents")
			final OutputBinding<String> writeToEventHub,
			final ExecutionContext context) {

		writeToEventHub.setValue(request.getBody());
		return request.createResponseBuilder(HttpStatus.OK).body("Event sent").build();
	}
}
