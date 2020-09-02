package eu.wecanconsulting.example.dbread;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import eu.wecanconsulting.example.dbtrigger.Task;

public class GetTaskHandler {

	@FunctionName("GetTask")
	public HttpResponseMessage getTask(
			@HttpTrigger(name = "getTaskTrigger",
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "tasks/{partitionKey}/{id}",
			methods = {HttpMethod.GET}) final
			HttpRequestMessage<Void> request,
			@CosmosDBInput(
					name = "getTask",
					databaseName = "ToDoList",
					collectionName = "Tasks",
					partitionKey = "{partitionKey}",
					id = "{id}",
					connectionStringSetting = "AzureCosmosDBConnection"
					) final
			Task task) {
		return request.createResponseBuilder(HttpStatus.OK).body(task).build();
	}
}
