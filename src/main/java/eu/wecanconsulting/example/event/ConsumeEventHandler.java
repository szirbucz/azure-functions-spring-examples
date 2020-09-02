package eu.wecanconsulting.example.event;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.Cardinality;
import com.microsoft.azure.functions.annotation.EventHubTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class ConsumeEventHandler extends AzureSpringBootRequestHandler<String, String>{
	@FunctionName("ConsumeEvent")
	public void todoToTaskDBTrigger(
			@EventHubTrigger(
					name = "consumeEvent",
					cardinality = Cardinality.ONE,
					connection = "EventHubConnection",
					eventHubName = "TodoEvents")
			final String event,
			final ExecutionContext context) {

		context.getLogger().info("Event handled: " + event);
	}
}
