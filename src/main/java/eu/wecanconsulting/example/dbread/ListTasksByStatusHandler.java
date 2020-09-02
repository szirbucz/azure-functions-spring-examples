package eu.wecanconsulting.example.dbread;

import java.util.List;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import eu.wecanconsulting.example.dbtrigger.Task;

public class ListTasksByStatusHandler {

	@FunctionName("TasksByStatus")
	public HttpResponseMessage listTasksByStatus(
			@HttpTrigger(name = "listTasksByStatusTrigger",
			authLevel = AuthorizationLevel.ANONYMOUS,
			route = "tasks/{status}",
			methods = {HttpMethod.GET}) final
			HttpRequestMessage<Void> request,
			@CosmosDBInput(
					name = "getTaskById",
					databaseName = "ToDoList",
					collectionName = "Tasks",
					connectionStringSetting = "AzureCosmosDBConnection",
					sqlQuery = "SELECT * FROM Items t WHERE t.status = {status}"
					) final
			List<Task> tasks) {
		return request.createResponseBuilder(HttpStatus.OK).body(tasks).build();
	}

}
