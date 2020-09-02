package eu.wecanconsulting.example.dbtrigger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import eu.wecanconsulting.example.Config;
import eu.wecanconsulting.example.dbwrite.TodoTestData;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Config.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TodoToTaskIT {

	private static final List<Todo> TODO_LIST = List.of(TodoTestData.SHOPPING, TodoTestData.WASHING);
	private static final String PATH = "/TodoToTask";

	@Autowired
	private WebTestClient client;

	@Test
	@DisplayName("Each Todo should be converted to Tasks")
	void testEachTasksAreConverted() {
		client.post()
		.uri(PATH)
		.body(Mono.just(TODO_LIST), List.class)
		.exchange().expectBody(Task[].class)
		.value(this::assertListHasTheExpectedValues);
	}

	private void assertListHasTheExpectedValues(final Task[] tasks) {
		assertEquals(tasks.length, TODO_LIST.size());
		IntStream.range(0, tasks.length).forEach(
				(i) -> assertTaskByTodo(TODO_LIST.get(i), tasks[i])
				);
	}

	private void assertTaskByTodo(final Todo todo, final Task task) {
		assertEquals(todo.getTask(), task.getDescription());
		assertEquals(TaskStatus.CREATED, task.getStatus());
		assertEquals(LocalDate.now(), task.getCreatedDate().toLocalDate());
	}

}

