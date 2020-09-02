package eu.wecanconsulting.example.dbtrigger;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.annotation.CosmosDBTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class GenerateTaskForTodoHandler extends AzureSpringBootRequestHandler<List<Todo>, List<Task>>{

	@FunctionName("TodoToTask")
	public void todoToTaskDBTrigger(
			@CosmosDBTrigger(
					name = "todo",
					databaseName = "ToDoList",
					collectionName = "Items",
					leaseCollectionName = "leasesTodo2",
					createLeaseCollectionIfNotExists = true,
					connectionStringSetting = "AzureCosmosDBConnection")
			final List<Todo> items,
			@CosmosDBOutput(
					name = "saveTask",
					databaseName = "ToDoList",
					collectionName = "Tasks",
					connectionStringSetting = "AzureCosmosDBConnection")
			final OutputBinding<List<Task>> writeToCosmos,
			final ExecutionContext context) {

		final List<Task> newTasks = handleRequest(items, context);
		writeToCosmos.setValue(newTasks);

		context.getLogger().info("Java Cosmos trigger processed items: \n" +
				String.join(",\n", newTasks.stream()
						.map(Task::getDescription)
						.collect(Collectors.toList())));
	}

}