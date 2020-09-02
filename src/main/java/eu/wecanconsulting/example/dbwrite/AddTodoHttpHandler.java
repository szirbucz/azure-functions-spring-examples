package eu.wecanconsulting.example.dbwrite;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import eu.wecanconsulting.example.dbtrigger.Todo;

public class AddTodoHttpHandler extends AzureSpringBootRequestHandler<Todo, Todo> {

	@FunctionName("AddTodo")
	public HttpResponseMessage addTodo(
			@HttpTrigger(
					name = "req",
					methods = {HttpMethod.GET, HttpMethod.POST},
					authLevel = AuthorizationLevel.ANONYMOUS)
			final HttpRequestMessage<Todo> request,
			@CosmosDBOutput(
					name = "database",
					databaseName = "ToDoList",
					collectionName = "Items",
					connectionStringSetting = "AzureCosmosDBConnection")
			final OutputBinding<Todo> db,
			final ExecutionContext context) {

		final Todo item = handleRequest(request.getBody(), context);
		db.setValue(item);

		return request.createResponseBuilder(HttpStatus.OK).body(String.format("Todo item %s has been added", item.getTask())).build();
	}

}
