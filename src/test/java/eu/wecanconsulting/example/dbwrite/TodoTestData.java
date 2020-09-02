package eu.wecanconsulting.example.dbwrite;

import java.util.UUID;

import eu.wecanconsulting.example.dbtrigger.Todo;

public class TodoTestData {

	public static final Todo WASHING = createToDo("WASHING");
	public static final UUID RANDOM_UUID = UUID.randomUUID();
	public static final Todo SHOPPING = createToDo("SHOPPING");
	public static final Todo NULLID = createTodoWithoutID("NULLID");

	private static Todo createToDo(final String task) {
		final Todo result = createTodoWithoutID(task);
		result.setId(UUID.randomUUID());
		return result;
	}

	private static Todo createTodoWithoutID(final String task) {
		final Todo result = new Todo();
		result.setTask(task);
		return result;
	}

}
