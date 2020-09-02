package eu.wecanconsulting.example.dbread;

import java.util.List;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import eu.wecanconsulting.example.dbtrigger.Task;
import eu.wecanconsulting.example.dbtrigger.TaskStatus;

public class ListTaskHandler extends AzureSpringBootRequestHandler<TaskStatus, List<Task>> {

	@FunctionName("ListTasks")
	public HttpResponseMessage listTasksByStatus(
			@HttpTrigger(
					name = "listTasks",
					authLevel = AuthorizationLevel.ANONYMOUS,
					route = "tasks",
					methods = {HttpMethod.GET}
					)
			final HttpRequestMessage<Void> request,
			@CosmosDBInput(
					name = "getTasks",
					databaseName = "ToDoList",
					collectionName = "Tasks",
					connectionStringSetting = "AzureCosmosDBConnection"
					)
			final List<Task> tasks,
			final ExecutionContext context) {

		return request.createResponseBuilder(HttpStatus.OK).body(tasks).build();
	}

}
