package eu.wecanconsulting.example.dbtrigger;

import java.sql.Date;
import java.util.UUID;

public class TaskTestData {
	public static final Task WASHING = createTask("WASHING", TaskStatus.CREATED);
	public static final Task SHOPPING = createTask("SHOPPING", TaskStatus.CREATED);
	public static final long CREATION_DATE = 1598694985L;

	private static Task createTask(final String description, final TaskStatus status) {
		final Task result = new Task();
		result.setId(UUID.randomUUID());
		result.setCreatedDate(new Date(CREATION_DATE));
		result.setDescription(description);
		result.setStatus(status);
		return result;
	}

}
