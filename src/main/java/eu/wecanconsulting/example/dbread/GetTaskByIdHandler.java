package eu.wecanconsulting.example.dbread;

import java.util.List;
import java.util.Optional;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import eu.wecanconsulting.example.dbtrigger.Task;

public class GetTaskByIdHandler {
	private static final String N_A = "N/A";
	private static final String ID = "id";
	private static final String NOT_FOUND_TEMPLATE = "Task not found with id [%s]";

	@FunctionName("GetTaskById")
	public HttpResponseMessage getTask(
			@HttpTrigger(name = "getTaskByIdTrigger",
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "task",
			methods = {HttpMethod.GET})
			final HttpRequestMessage<Optional<String>> request,
			@CosmosDBInput(
					name = "getTaskById",
					databaseName = "ToDoList",
					collectionName = "Tasks",
					connectionStringSetting = "AzureCosmosDBConnection",
					sqlQuery = "SELECT * FROM Items t WHERE t.id={Query}.id"
					)
			final List<Task> task) {
		if (task.isEmpty()) {
			final String message = String.format(NOT_FOUND_TEMPLATE, request.getQueryParameters().getOrDefault(ID, N_A));
			return request.createResponseBuilder(HttpStatus.NOT_FOUND).body(message).build();
		}
		return request.createResponseBuilder(HttpStatus.OK).body(task.get(0)).build();
	}
}
